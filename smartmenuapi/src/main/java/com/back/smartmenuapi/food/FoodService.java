//Este archivo es una interfaz que define los métodos que se implementarán en la clase FoodServiceImplement

package com.back.smartmenuapi.food;

import com.back.smartmenuapi.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface FoodService {

    Food saveFood(Food food);
    List<Food> findAllFood();
    Food findByNameIgnoreCase(String name) throws NotFoundException;
    Food updateFood(Long id, Food food) throws NotFoundException;
    void deleteFood(Long id) throws NotFoundException;

}
