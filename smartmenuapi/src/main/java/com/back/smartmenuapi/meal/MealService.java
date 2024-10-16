package com.back.smartmenuapi.meal;

import com.back.smartmenuapi.error.NotFoundException;

import java.util.List;

public interface MealService {

    Meal saveMeal(Meal meal);
    List<Meal> findAllMeal();
    Meal findMealById(Long id) ;
    Meal findByNameIgnoreCase(String name) ;
    Meal updateMeal(Long id, Meal meal) ;
    void deleteMeal(Long id) ;

}
