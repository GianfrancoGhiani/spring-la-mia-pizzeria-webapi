package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "name", required = false) Optional<String> name){
        if (name.isPresent()){
            List<Pizza> menu = pizzaRepository.findByNameContainingIgnoreCase(name.get());
            model.addAttribute("list", menu);
            model.addAttribute("queryValue", name.get());
        } else {
            List<Pizza> menu = pizzaRepository.findAll();
            model.addAttribute("list", menu);
        }
        return "/pizzas/index";

    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id){
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "This article is not in our database"));
        model.addAttribute("pizza", pizza);
        return "/pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create";
    }
    @PostMapping("/create")
    public String createDBInstance(@ModelAttribute("pizza") Pizza form, Model model){

        pizzaRepository.save(form);
        return "redirect:/pizzas";
    }

}
