package org.learning.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "name", required = false) Optional<String> name){
        if (name.isPresent()){
            List<Pizza> menu = pizzaService.getFilteredPizzas(name.get());
            model.addAttribute("list", menu);
            model.addAttribute("queryValue", name.get());
        } else {
            List<Pizza> menu = pizzaService.getAllPizzas();
            model.addAttribute("list", menu);
        }
        return "/pizzas/index";

    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id){

        try{
            Pizza pizza = pizzaService.getPizzaById(id);
            model.addAttribute("pizza", pizza);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article is not in our database");
        }

        return "/pizzas/show";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create";
    }
    @PostMapping("/create")
    public String createDBInstance(@Valid @ModelAttribute("pizza") Pizza form,  BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/pizzas/create";
        }
        pizzaService.createPizza(form);
        return "redirect:/pizzas";
    }

}
