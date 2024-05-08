package com.back.smartmenuapi.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientServiceImplement implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findIngredientById(Long id) {
        return ingredientRepository.findById(id).get();
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient ingredientDb = ingredientRepository.findById(id).get();
        if (Objects.nonNull(ingredient.getName()) && !"".equalsIgnoreCase(ingredient.getName())) {
            ingredientDb.setName(ingredient.getName());
        }
        if (Objects.nonNull(ingredient.getQuantity())) {
            ingredientDb.setQuantity(ingredient.getQuantity());
        }
        return ingredientRepository.save(ingredientDb);
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
