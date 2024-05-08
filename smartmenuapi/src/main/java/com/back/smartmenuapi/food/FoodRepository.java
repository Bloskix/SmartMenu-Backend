package com.back.smartmenuapi.food;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByNameIgnoreCase(String name);
}
