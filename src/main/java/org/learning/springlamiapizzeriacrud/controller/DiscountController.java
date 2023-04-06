package org.learning.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.model.AlertMessage;
import org.learning.springlamiapizzeriacrud.model.AlertMessage.AlertMessageType;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.model.SpecialDiscount;
import org.learning.springlamiapizzeriacrud.service.DiscountService;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private DiscountService discountService;
    @GetMapping("/create")
    public String createDiscountView(@RequestParam("p_id")Integer p_id, Model model, RedirectAttributes redirectAttributes){
        try {
            Pizza pizza = pizzaService.getPizzaById(p_id);
            SpecialDiscount discount = new SpecialDiscount();
            discount.setPizza(pizza);
            model.addAttribute("discount", discount);
            return "/discounts/editCreate";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.ERROR, "Error finding your pizza"));
            return "redirect:/pizzas";
        }
    }

    @PostMapping("/create")
    public String createDiscountDB(
            @Valid @ModelAttribute("discount") SpecialDiscount discount, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/discounts/editCreate";
        }
        System.out.println(discount);
        discountService.createDiscount(discount);
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "Discount Added"));
        return "redirect:/pizzas/"+Integer.toString(discount.getPizza().getId());
    }
    @GetMapping("/edit/{d_id}")
    public String editDiscountView(@PathVariable("d_id")Integer d_id, Model model, RedirectAttributes redirectAttributes){
        try{
            SpecialDiscount discount = discountService.getDiscountById(d_id);
            model.addAttribute("discount", discount);
            return "/discounts/editCreate";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.ERROR, "Error finding your pizza"));
            return "redirect:/pizzas";
        }
    }

    @PostMapping("/edit/{d_id}")
    public String editDiscountDB(
            @PathVariable("d_id")Integer d_id, @Valid @ModelAttribute("discount") SpecialDiscount discount, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/discounts/editCreate";
        }
        System.out.println(d_id);
        discountService.updateDiscount(discount, d_id);
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "Discount " + discount.getTitle() +" Updated"));
        return "redirect:/pizzas/"+Integer.toString(discount.getPizza().getId());
    }
    @GetMapping("/delete/{d_id}")
    public String delete(@PathVariable Integer d_id, RedirectAttributes redirectAttributes) {
        Integer pizzaId = discountService.getDiscountById(d_id).getPizza().getId();
        try{
            boolean deleted = discountService.deleteById(d_id);
            if (deleted){
                redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "Discount deleted"));
            } else {
                redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.ERROR,"Unable to delete this item"));
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.ERROR,"No Discount founded"));
        }
        return "redirect:/pizzas/" + pizzaId;
    }
}
