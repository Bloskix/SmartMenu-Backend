package com.back.smartmenuapi.meal;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    MealService mealService;

    @PostMapping
    public Meal saveMeal(@Valid @RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

    @GetMapping
    public List<Meal> findAll() {
        return mealService.findAllMeal();
    }

    @GetMapping("/{id}")
    public Meal findMealById(@PathVariable Long id) {
        return mealService.findMealById(id);
    }

    @GetMapping("/name/{name}")
    public Meal findByNameIgnoreCase(@PathVariable String name) {
        return mealService.findByNameIgnoreCase(name);
    }

    @GetMapping("/type/{type}")
    public List<Meal> findMealsByType(@PathVariable MealType type) {
        return mealService.findMealsByType(type);
    }

    @PutMapping("/{id}")
    public Meal updateMeal(@PathVariable Long id, @RequestBody Meal meal) {
        System.out.println("ID recibido en el backend: " + id);
        return mealService.updateMeal(id, meal);
    }

    @DeleteMapping("/{id}")
    public String deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return "Meal deleted";
    }
}
