package com.back.smartmenuapi.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.findAllIngredients();
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        return ingredientService.findIngredientById(id);
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id, @RequestParam int quantity) {
        ingredientService.deleteIngredient(id, quantity);
    }
}
