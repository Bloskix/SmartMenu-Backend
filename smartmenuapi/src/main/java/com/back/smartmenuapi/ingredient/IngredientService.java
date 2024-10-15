package com.back.smartmenuapi.ingredient;

import com.back.smartmenuapi.error.NotFoundException;

import java.util.List;

public interface IngredientService {

    Ingredient saveIngredient(Ingredient ingredient);

    List<Ingredient> findAllIngredients();

    Ingredient findIngredientById(Long id);

    Ingredient findByName(String name);

    Ingredient updateIngredient(Long id, Ingredient ingredient);

    void deleteIngredient(Long id, int quantity);
}
