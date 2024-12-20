package com.back.smartmenuapi.meal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Optional<Meal> findByNameIgnoreCase(String name);

    List<Meal> findByType(MealType type);
}
