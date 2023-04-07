package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.model.Ingredient;
import org.learning.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getAll(){
        return ingredientRepository.findAll();
    }
    public Ingredient getById(Integer id){return ingredientRepository.findById(id).orElseThrow(()->new RuntimeException("no ingredient found"));}
    public Ingredient create(Ingredient ingredient){
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredient.getName());
        newIngredient.setDescription(ingredient.getDescription());
        return ingredientRepository.save(newIngredient);
    }
    public Ingredient update(Ingredient ingredient, Integer id){
        Ingredient newIngredient = ingredientRepository.findById(id).orElseThrow(()->new RuntimeException("no ingredient found"));
        if (!ingredient.getName().isEmpty()){
            newIngredient.setName(ingredient.getName());
        }
        newIngredient.setDescription(ingredient.getDescription());
        return ingredientRepository.save(newIngredient);
    }
    public void deleteById(Integer id){
        ingredientRepository.deleteById(id);
    }
}
