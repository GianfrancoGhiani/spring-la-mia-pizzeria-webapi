package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
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
    private LocalDate startingDate;

    @Column(nullable = false)
    private LocalDate expiringDate;

    @Column(nullable = false)
    @NotEmpty(message = "the Discount must have a title")
    @Size(max = 30, message = "This title is too long (max 30 char)")
    private String title;

    @ManyToOne
    private Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public SpecialDiscount(){}
    public SpecialDiscount(Integer id, LocalDate startingDate, LocalDate expiringDate, String title) {
        this.id = id;
        this.startingDate = startingDate;
        this.expiringDate = expiringDate;
        this.title = title;
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
