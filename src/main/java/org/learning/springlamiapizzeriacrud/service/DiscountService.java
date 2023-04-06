package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.model.SpecialDiscount;
import org.learning.springlamiapizzeriacrud.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    public SpecialDiscount getDiscountById(Integer id){
        return discountRepository.findById(id).orElseThrow(()->new RuntimeException("no discount founded"));
    }

    public SpecialDiscount createDiscount(SpecialDiscount form){
        SpecialDiscount discountToSave = new SpecialDiscount();
        discountToSave.setTitle(form.getTitle());
        discountToSave.setValue(form.getValue());
        discountToSave.setStartingDate(form.getStartingDate());
        discountToSave.setExpiringDate(form.getExpiringDate());
        discountToSave.setPizza(form.getPizza());
        return discountRepository.save(discountToSave);
    }
    public SpecialDiscount updateDiscount(SpecialDiscount form, Integer id){
        SpecialDiscount discountToUpdate = getDiscountById(id);
        discountToUpdate.setTitle(form.getTitle());
        discountToUpdate.setValue(form.getValue());
        if (form.getStartingDate() != null){
            discountToUpdate.setStartingDate(form.getStartingDate());
        }
        if (form.getExpiringDate() != null){
            discountToUpdate.setExpiringDate(form.getExpiringDate());
        }
        discountToUpdate.setPizza(form.getPizza());
        return discountRepository.save(discountToUpdate);
    }

    public boolean deleteById(Integer id){
        discountRepository.findById(id).orElseThrow(()->new RuntimeException("no discount founded"));
        try{
            discountRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
