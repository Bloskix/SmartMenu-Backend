package com.back.smartmenuapi.meal;

import com.back.smartmenuapi.ingredient.IngredientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImplement implements MealService {

    @Autowired
    MealRepository mealRepository;

    @Autowired
    IngredientService ingredientService;

    @Override
    public Meal saveMeal(Meal meal) {
        meal.getIngredients().forEach(ingredient -> {
            ingredientService.saveIngredient(ingredient);
        });

        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> findAllMeal() {
        return mealRepository.findAll();
    }

    @Override
    public Meal findMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));
    }

    @Override
    public Meal findByNameIgnoreCase(String name) {
        return mealRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));
    }

    @Override
    public Meal updateMeal(Long id, Meal meal) {
        Meal existingMeal = mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));

        if (meal.getName() != null) {
            existingMeal.setName(meal.getName());
        }

        if (meal.getPrepTime() != null) {
            existingMeal.setPrepTime(meal.getPrepTime());
        }

        if (meal.getType() != null) {
            existingMeal.setType(meal.getType());
        }

        if (meal.getIngredients() != null) {
            meal.getIngredients().forEach(ingredient -> {
                ingredientService.updateIngredient(ingredient.getId(), ingredient);
            });
        }

        return mealRepository.save(existingMeal);
    }

    @Override
    public void deleteMeal(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));

        meal.getIngredients().forEach(ingredient -> {
            ingredientService.deleteIngredient(ingredient.getId());
        });

        mealRepository.deleteById(id);
    }

    public boolean enoughMeals() {
        long count = mealRepository.count();
        return count >= 14;
    }
}
