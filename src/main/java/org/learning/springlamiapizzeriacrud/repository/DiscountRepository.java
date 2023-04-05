package org.learning.springlamiapizzeriacrud.repository;

import org.learning.springlamiapizzeriacrud.model.SpecialDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<SpecialDiscount, Integer> {
    
}
