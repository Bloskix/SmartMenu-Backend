package com.back.smartmenuapi.meal;

import com.back.smartmenuapi.error.NotFoundException;
import com.back.smartmenuapi.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MealServiceImplement implements MealService {

    @Autowired
    MealRepository mealRepository;
    @Autowired
    IngredientService ingredientService;

    @Override
    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> findAllMeal() {
        return mealRepository.findAll();
    }


    @Override
    public Meal findByNameIgnoreCase(String name) throws NotFoundException {
        Optional<Meal> meal = mealRepository.findByNameIgnoreCase(name);
        if (meal.isEmpty()) {
            throw new NotFoundException("Meal not found in database");
        }
        return meal.get();
    }

    @Override
    public Meal updateMeal(Long id, Meal meal) throws NotFoundException {
        Optional<Meal> mealOptional = mealRepository.findById(id);
        if (mealOptional.isEmpty()) {
            throw new NotFoundException("Meal not found in database");
        }
        if (Objects.nonNull(meal.getPrepTime())) {
            meal.setPrepTime(meal.getPrepTime());
        }
        if (Objects.nonNull(meal.getIngredients())) {
            meal.setIngredients(meal.getIngredients());
        }
        return mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(Long id) throws NotFoundException {
        Optional<Meal> meal = mealRepository.findById(id);
        if (meal.isEmpty()) {
            throw new NotFoundException("Meal not found in database");
        }
        mealRepository.deleteById(id);
    }
}
