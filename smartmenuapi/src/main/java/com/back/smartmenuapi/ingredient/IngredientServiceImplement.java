package com.back.smartmenuapi.ingredient;

import jakarta.persistence.EntityNotFoundException;
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
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
    }

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient ingredientDb = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

        if (Objects.nonNull(ingredient.getName()) && !"".equalsIgnoreCase(ingredient.getName())) {
            ingredientDb.setName(ingredient.getName());
        }

        if (Objects.nonNull(ingredient.getQuantity())) {
            ingredient.validateQuantity();
            ingredientDb.setQuantity(ingredient.getQuantity());
        }

        return ingredientRepository.save(ingredientDb);
    }

    @Override
    public void deleteIngredient(Long id) {
        Ingredient ingredientDb = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

        ingredientRepository.delete(ingredientDb);
        }
}

