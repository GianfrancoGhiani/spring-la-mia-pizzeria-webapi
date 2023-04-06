package org.learning.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.model.AlertMessage;
import org.learning.springlamiapizzeriacrud.model.Ingredient;
import org.learning.springlamiapizzeriacrud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;


    @GetMapping
    public String index(Model model){
        model.addAttribute("list", ingredientService.getAll());
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/index";
    }

    @PostMapping("/create")
    public String create(@Valid@ModelAttribute(name = "ingredient")Ingredient ingredient, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("list", ingredientService.getAll());
            return "ingredients/index";
        }
        ingredientService.create(ingredient);
        return "redirect:/ingredients";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try{
            ingredientService.deleteById(id);
            return "redirect:/ingredients";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Unable to delete this ingredient"));
            return "redirect:/ingredients";
        }

    }
}
