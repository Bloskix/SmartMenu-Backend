package com.back.smartmenuapi.mealTest;

import com.back.smartmenuapi.ingredient.Ingredient;
import com.back.smartmenuapi.meal.Meal;
import com.back.smartmenuapi.meal.MealController;
import com.back.smartmenuapi.meal.MealService;
import com.back.smartmenuapi.meal.MealType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MealController.class)
public class MealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @Autowired
    private ObjectMapper objectMapper;

    private Meal meal;

    private Ingredient ingredient;

    @BeforeEach
    public void setUp() {
        ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Tomato");
        ingredient.setQuantity(2);

        meal = new Meal();
        meal.setId(1L);
        meal.setName("Pizza");
        meal.setIngredients(Arrays.asList(ingredient));
        meal.setType(MealType.MEAL);
        meal.setPrepTime(30);
    }

    @Test
    public void testSaveMeal() throws Exception {
        Mockito.when(mealService.saveMeal(any(Meal.class))).thenReturn(meal);

        mockMvc.perform(post("/meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pizza"));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Meal> meals = Arrays.asList(meal, new Meal());
        Mockito.when(mealService.findAllMeal()).thenReturn(meals);

        mockMvc.perform(get("/meals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"));
    }

    @Test
    public void testFindMealById() throws Exception {
        Mockito.when(mealService.findMealById(anyLong())).thenReturn(meal);

        mockMvc.perform(get("/meals/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pizza"));
    }

    @Test
    public void testFindByNameIgnoreCase() throws Exception {
        Mockito.when(mealService.findByNameIgnoreCase(eq("pizza"))).thenReturn(meal);

        mockMvc.perform(get("/meals/{name}", "pizza"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pizza"));
    }

    @Test
    public void testUpdateMeal() throws Exception {
        Meal updatedMeal = new Meal();
        updatedMeal.setId(1L);
        updatedMeal.setName("Updated Pizza");

        Mockito.when(mealService.updateMeal(eq(1L), any(Meal.class))).thenReturn(updatedMeal);

        mockMvc.perform(put("/meals/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMeal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Pizza"));
    }

    @Test
    public void testDeleteMeal() throws Exception {
        mockMvc.perform(delete("/meals/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Meal deleted"));
    }
}

