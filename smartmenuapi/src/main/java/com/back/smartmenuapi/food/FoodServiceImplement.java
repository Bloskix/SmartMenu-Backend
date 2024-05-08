package com.back.smartmenuapi.food;

import com.back.smartmenuapi.error.NotFoundException;
import com.back.smartmenuapi.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FoodServiceImplement implements FoodService{

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    IngredientService ingredientService;

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> findAllFood() {
        return foodRepository.findAll();
    }


    @Override
    public Food findByNameIgnoreCase(String name) throws NotFoundException {
        Optional<Food> food = foodRepository.findByNameIgnoreCase(name);
        if (food.isEmpty()) {
            throw new NotFoundException("Food not found in database");
        }
        return food.get();
    }

    @Override
    public Food updateFood(Long id, Food food) throws NotFoundException {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if (foodOptional.isEmpty()) {
            throw new NotFoundException("Food not found in database");
        }
        if (Objects.nonNull(food.getPrepTime())) {
            food.setPrepTime(food.getPrepTime());
        }
        if (Objects.nonNull(food.getIngredients())) {
            food.setIngredients(food.getIngredients());
        }
        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long id) throws NotFoundException {
        Optional<Food> food = foodRepository.findById(id);
        if (food.isEmpty()) {
            throw new NotFoundException("Food not found in database");
        }
        foodRepository.deleteById(id);
    }
}
