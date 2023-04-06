package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(max = 30,message = "the name can have at least 30 char")
    private String name;
    @Lob
    @Size(max = 200,message = "the name can have at least 200 char")
    private String description;

    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizzas;

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

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
