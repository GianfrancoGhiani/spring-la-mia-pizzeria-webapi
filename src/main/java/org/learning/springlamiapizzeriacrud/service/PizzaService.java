package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.exceptions.IngredientNotFoundException;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.model.Ingredient;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.IngredientRepository;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    public Pizza createPizza(Pizza form){
        Pizza pizzaToDB = new Pizza();
        pizzaToDB.setName(form.getName());
        pizzaToDB.setDescription(form.getDescription());
        pizzaToDB.setPrice(form.getPrice());
        pizzaToDB.setIngredients(getPizzaIngredients(form));
        return pizzaRepository.save(pizzaToDB);
    }
    public Pizza updatePizza(Pizza form, Integer id){
        Pizza pizzaToDB = getPizzaById(id);
        pizzaToDB.setName(form.getName());
        pizzaToDB.setDescription(form.getDescription());
        pizzaToDB.setPrice(form.getPrice());
        pizzaToDB.setIngredients(getPizzaIngredients(form));
        return pizzaRepository.save(pizzaToDB);
    }

    private List<Ingredient> getPizzaIngredients(Pizza form) {
        List<Ingredient> formIngredients = new ArrayList<>();

        if (form.getIngredients() != null) {
            for (Ingredient i : form.getIngredients()) {
                formIngredients.add(ingredientRepository.findById(i.getId()).orElseThrow( ()-> new IngredientNotFoundException("Ingredient not found")));
            }
        }
        return formIngredients;
    }


    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }
    public List<Pizza> getFilteredPizzas(String query){
        return pizzaRepository.findByNameContainingIgnoreCase(query);
    }

    public Pizza getPizzaById(Integer id) throws RuntimeException{
        return pizzaRepository.findById(id).orElseThrow(()->new PizzaNotFoundException("no pizzas found"));
    }

    public boolean deleteById(Integer id) throws RuntimeException{
        //try to find the pizza into the DB
        pizzaRepository.findById(id).orElseThrow(()->new PizzaNotFoundException("no pizzas found"));
        //if founded it will continue, else it throws
        try{
            //'cause pizza was fins, delete this item
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public boolean isUniqueName(Pizza pizza, Integer id){
        if (id == null){
            return !pizzaRepository.existsByName(pizza.getName());
        }
        return !pizzaRepository.existsByNameAndIdNot(pizza.getName(), pizza.getId());
    };

}