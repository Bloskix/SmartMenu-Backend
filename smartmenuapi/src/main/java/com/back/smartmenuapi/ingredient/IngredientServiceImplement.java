package com.back.smartmenuapi.ingredient;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientServiceImplement implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        ingredient.validateQuantity();
        Optional<Ingredient> existingIngredient = ingredientRepository.findByNameIgnoreCase(ingredient.getName());

        if (existingIngredient.isPresent()) {
            Ingredient ingredientDb = existingIngredient.get();
            ingredientDb.setQuantity(ingredientDb.getQuantity() + ingredient.getQuantity());
            return ingredientRepository.save(ingredientDb);
        } else {
            return ingredientRepository.save(ingredient);
        }
    }

    @Override
    public List<Ingredient> findAllIngredients() {
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
        ingredient.validateQuantity();

        Ingredient ingredientDb = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

        if (Objects.nonNull(ingredient.getName()) && !"".equalsIgnoreCase(ingredient.getName())) {
            ingredientDb.setName(ingredient.getName());
        }

        if (Objects.nonNull(ingredient.getQuantity())) {
            ingredientDb.setQuantity(ingredient.getQuantity());
        }

        return ingredientRepository.save(ingredientDb);
    }

    @Override
    public void deleteIngredient(Long id, int quantity) {
        Ingredient ingredientDb = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

        int newQuantity = ingredientDb.getQuantity() - quantity;

        if (newQuantity <= 0) {
            ingredientRepository.delete(ingredientDb);
        } else {
            ingredientDb.setQuantity(newQuantity);
            ingredientRepository.save(ingredientDb);
        }
    }
}

