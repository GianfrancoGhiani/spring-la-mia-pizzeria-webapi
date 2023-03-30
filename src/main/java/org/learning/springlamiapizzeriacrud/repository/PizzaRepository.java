package org.learning.springlamiapizzeriacrud.repository;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNameContainingIgnoreCase(String name);
}
