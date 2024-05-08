package com.back.smartmenuapi.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @PostMapping("/saveIngredient")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    @GetMapping("/findAllIngredients")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.findAllIngredients();
    }

    @GetMapping("/findIngredientById/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        return ingredientService.findIngredientById(id);
    }

    @PutMapping("/updateIngredient/{id}")
    public Ingredient updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/deleteIngredient/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
    }
}