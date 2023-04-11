package org.learning.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "discounts")
public class SpecialDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Min(value = 0, message = "the discount value must be greater than zero")
    private double value;

    @Column(nullable = false)
    private LocalDate startingDate;

    @Column(nullable = false)
    private LocalDate expiringDate;

    @Column(nullable = false)
    @NotEmpty(message = "the Discount must have a title")
    @Size(max = 30, message = "This title is too long (max 30 char)")
    private String title;

    @JsonIgnore
    @ManyToOne
    private Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public SpecialDiscount(){}

    public SpecialDiscount(Integer id, double value, LocalDate startingDate, LocalDate expiringDate, String title, Pizza pizza) {
        this.id = id;
        this.value = value;
        this.startingDate = startingDate;
        this.expiringDate = expiringDate;
        this.title = title;
        this.pizza = pizza;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SpecialDiscount{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", expiringDate=" + expiringDate +
                ", title='" + title + '\'' +
                '}';
    }
}
