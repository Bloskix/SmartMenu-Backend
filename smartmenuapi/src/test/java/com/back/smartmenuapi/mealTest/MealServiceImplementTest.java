package com.back.smartmenuapi.mealTest;

import com.back.smartmenuapi.ingredient.Ingredient;
import com.back.smartmenuapi.ingredient.IngredientService;
import com.back.smartmenuapi.meal.Meal;
import com.back.smartmenuapi.meal.MealRepository;
import com.back.smartmenuapi.meal.MealServiceImplement;
import com.back.smartmenuapi.meal.MealType;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealServiceImplementTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private MealServiceImplement mealServiceImplement;

    private Meal meal;
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Tomato");
        ingredient.setQuantity(2);

        meal = new Meal();
        meal.setId(1L);
        meal.setName("Pasta");
        meal.setIngredients(Arrays.asList(ingredient));
        meal.setType(MealType.MEAL);
        meal.setPrepTime(30);
    }

    @Test
    void testSaveMeal() {
        when(mealRepository.save(meal)).thenReturn(meal);

        Meal savedMeal = mealServiceImplement.saveMeal(meal);

        verify(ingredientService, times(1)).saveIngredient(ingredient);
        verify(mealRepository, times(1)).save(meal);

        assertNotNull(savedMeal);
        assertEquals("Pasta", savedMeal.getName());
    }

    @Test
    void testFindAllMeal() {
        when(mealRepository.findAll()).thenReturn(List.of(meal));

        List<Meal> meals = mealServiceImplement.findAllMeal();

        verify(mealRepository, times(1)).findAll();

        assertFalse(meals.isEmpty());
        assertEquals(1, meals.size());
    }

    @Test
    void testFindMealById_Success() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        Meal foundMeal = mealServiceImplement.findMealById(1L);

        assertNotNull(foundMeal);
        assertEquals("Pasta", foundMeal.getName());
    }

    @Test
    void testFindMealById_NotFound() {
        when(mealRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealServiceImplement.findMealById(1L));
    }

    @Test
    void testUpdateMeal_Success() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
        when(mealRepository.save(meal)).thenReturn(meal);

        Meal updatedMeal = new Meal();
        updatedMeal.setName("Updated Pasta");

        Meal result = mealServiceImplement.updateMeal(1L, updatedMeal);

        assertEquals("Updated Pasta", result.getName());
        verify(mealRepository, times(1)).save(meal);
    }

    @Test
    void testDeleteMeal_Success() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        mealServiceImplement.deleteMeal(1L);

        verify(ingredientService, times(1)).deleteIngredient(ingredient.getId(), ingredient.getQuantity());
        verify(mealRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteMeal_NotFound() {
        when(mealRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealServiceImplement.deleteMeal(1L));
    }
}

