package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza createPizza(Pizza form){
        Pizza pizzaToDB = new Pizza();
        pizzaToDB.setName(form.getName());
        pizzaToDB.setDescription(form.getDescription());
        pizzaToDB.setPrice(form.getPrice());
        return pizzaRepository.save(pizzaToDB);
    }

    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }
    public List<Pizza> getFilteredPizzas(String query){
        return pizzaRepository.findByNameContainingIgnoreCase(query);
    }

    public Pizza getPizzaById(Integer id) throws RuntimeException{
        return pizzaRepository.findById(id).orElseThrow(()->new RuntimeException("not pizzas founded"));
    }
}