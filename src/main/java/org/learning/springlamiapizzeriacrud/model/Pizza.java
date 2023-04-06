package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "the name cannot be empty")
    @Size(max = 30, message = "This name is too long (max 30 char)")
    private String name;
    @Lob
    @Column(nullable = false)
    @NotEmpty(message = "the description cannot be empty")
    @Size(max = 300, message = "This description is too long (max 300 char)")
    private String description;
    @Column(nullable = false)
    @Positive(message = "the price must be greater than 0")
    private double price;




    @OneToMany(mappedBy = "pizza")
    private List<SpecialDiscount> discounts;
    public List<SpecialDiscount> getDiscounts() {
        return discounts;
    }

    public boolean hasAvailableDiscount(){
        if (discounts != null){
            for (SpecialDiscount d: discounts) {
                if (d.getExpiringDate().isAfter(LocalDate.now()))return true;
            }
        }
        return false;
    }
    public double getDiscountedPrice(){
        if (hasAvailableDiscount()){
            double tempPrice = this.price;
            for (SpecialDiscount d: discounts) {
                if (!d.getExpiringDate().isBefore(LocalDate.now()) && !d.getStartingDate().isAfter(LocalDate.now())){
                    tempPrice = tempPrice - (tempPrice * (d.getValue()/100));
                }
            }
            return tempPrice;
        }
        return price;
    }
    public void setDiscounts(List<SpecialDiscount> discounts) {
        this.discounts = discounts;

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
