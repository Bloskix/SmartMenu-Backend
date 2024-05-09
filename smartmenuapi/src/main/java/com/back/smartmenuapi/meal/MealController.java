package com.back.smartmenuapi.meal;

import com.back.smartmenuapi.error.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MealController {

    @Autowired
    MealService mealService;

    @PostMapping("/saveMeal")
    public Meal saveMeal(@Valid @RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

    @GetMapping("/findAllMeal")
    public List<Meal> findAll() {
        return mealService.findAllMeal();
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    Meal findByNameIgnoreCase(@PathVariable String name) throws NotFoundException {
        return mealService.findByNameIgnoreCase(name);
    }

    @PutMapping("/updateMeal/{id}")
    public Meal updateMeal(@PathVariable Long id, @RequestBody Meal meal) throws NotFoundException {
        return mealService.updateMeal(id, meal);
    }

    @DeleteMapping("/deleteMeal/{id}")
    public String deleteMeal(@PathVariable Long id) throws NotFoundException {
        mealService.deleteMeal(id);
        return "Meal deleted";
    }
}
