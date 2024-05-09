//Este archivo es una interfaz que define los métodos que se implementarán en la clase MealServiceImplement

package com.back.smartmenuapi.meal;

import com.back.smartmenuapi.error.NotFoundException;

import java.util.List;

public interface MealService {

    Meal saveMeal(Meal meal);
    List<Meal> findAllMeal();
    Meal findByNameIgnoreCase(String name) throws NotFoundException;
    Meal updateMeal(Long id, Meal meal) throws NotFoundException;
    void deleteMeal(Long id) throws NotFoundException;

}
