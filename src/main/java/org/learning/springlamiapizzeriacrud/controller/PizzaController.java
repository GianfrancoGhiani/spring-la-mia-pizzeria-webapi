package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model){
        List<Pizza> menu = pizzaRepository.findAll();
        model.addAttribute("list", menu);
        return "/pizzas/index";
    }
    @GetMapping("/search")
    public String filtered(Model model, @RequestParam(name = "name")String name){
        List<Pizza> filteredMenu = pizzaRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("list", filteredMenu);
        return "/pizzas/index";
    }

}
