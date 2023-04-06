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
        pizzaToDB.setIngredients(form.getIngredients());
        return pizzaRepository.save(pizzaToDB);
    }
    public Pizza updatePizza(Pizza form, Integer id){
        Pizza pizzaToDB = getPizzaById(form.getId());
        pizzaToDB.setName(form.getName());
        pizzaToDB.setDescription(form.getDescription());
        pizzaToDB.setPrice(form.getPrice());
        pizzaToDB.setIngredients(form.getIngredients());
        return pizzaRepository.save(pizzaToDB);
    }

    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }
    public List<Pizza> getFilteredPizzas(String query){
        return pizzaRepository.findByNameContainingIgnoreCase(query);
    }

    public Pizza getPizzaById(Integer id) throws RuntimeException{
        return pizzaRepository.findById(id).orElseThrow(()->new RuntimeException("no pizzas founded"));
    }

    public boolean deleteById(Integer id) throws RuntimeException{
        //try to find the pizza into the DB
        pizzaRepository.findById(id).orElseThrow(()->new RuntimeException("no pizzas founded"));
        //if founded it will continue, else it throws
        try{
            //'cause pizza was fins, delete this item
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public boolean isUniqueName(Pizza pizza){
        if (pizza.getId() == null){
            return !pizzaRepository.existsByName(pizza.getName());
        }
        return !pizzaRepository.existsByNameAndIdNot(pizza.getName(), pizza.getId());
    };

}