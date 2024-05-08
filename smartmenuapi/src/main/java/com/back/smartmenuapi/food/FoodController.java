package com.back.smartmenuapi.food;

import com.back.smartmenuapi.error.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping("/saveFood")
    public Food saveFood(@Valid @RequestBody Food food) {
        return foodService.saveFood(food);
    }

    @GetMapping("/findAllFood")
    public List<Food> findAll() {
        return foodService.findAllFood();
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    Food findByNameIgnoreCase(@PathVariable String name) throws NotFoundException {
        return foodService.findByNameIgnoreCase(name);
    }

    @PutMapping("/updateFood/{id}")
    public Food updateFood(@PathVariable Long id, @RequestBody Food food) throws NotFoundException {
        return foodService.updateFood(id, food);
    }

    @DeleteMapping("/deleteFood/{id}")
    public String deleteFood(@PathVariable Long id) throws NotFoundException {
        foodService.deleteFood(id);
        return "Food deleted";
    }
}
