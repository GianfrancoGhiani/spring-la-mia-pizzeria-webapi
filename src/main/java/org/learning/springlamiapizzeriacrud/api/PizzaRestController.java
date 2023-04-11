package org.learning.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    @Autowired
    PizzaService pizzaService;
    @GetMapping
    public List<Pizza> getAllPizzas(@RequestParam(name = "name", required = false) Optional<String> name){
        if (name.isPresent()){
            return pizzaService.getFilteredPizzas(name.get());
        }
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public Pizza getPizzaById(@PathVariable("id")Integer id){
        try {
            return pizzaService.getPizzaById(id);

        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Pizza pizza(@Valid @ModelAttribute Pizza pizzaForm){
        if (!pizzaService.isUniqueName(pizzaForm, null)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"errors\": \"The name must be unique\"}");
        }
        return pizzaService.createPizza(pizzaForm);
    }

    @PutMapping("/{id}")
    public Pizza pizza(@Valid @ModelAttribute Pizza pizzaForm, @PathVariable("id") Integer id){
        if (!pizzaService.isUniqueName(pizzaForm, id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"errors\": \"The name must be unique\"}");
        }

        try {
            return pizzaService.updatePizza(pizzaForm, id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        try {
            boolean success = pizzaService.deleteById(id);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Unable to delete this element");
            }
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
