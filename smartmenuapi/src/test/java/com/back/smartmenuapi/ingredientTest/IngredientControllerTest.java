package com.back.smartmenuapi.ingredientTest;

import com.back.smartmenuapi.ingredient.Ingredient;
import com.back.smartmenuapi.ingredient.IngredientController;
import com.back.smartmenuapi.ingredient.IngredientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IngredientController.class)
public class IngredientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testGetAllIngredients() throws Exception {
        Ingredient ingredient1 = new Ingredient(1L, "Tomato", 10, null);
        Ingredient ingredient2 = new Ingredient(2L, "Onion", 5, null);
        List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2);

        when(ingredientService.findAllIngredients()).thenReturn(ingredients);

        mockMvc.perform(get("/ingredients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tomato"))
                .andExpect(jsonPath("$[1].name").value("Onion"));

        verify(ingredientService, times(1)).findAllIngredients();
    }

    @Test
    public void testGetIngredientById_Found() throws Exception {
        Ingredient ingredient = new Ingredient(1L, "Tomato", 10, null);

        when(ingredientService.findIngredientById(1L)).thenReturn(ingredient);

        mockMvc.perform(get("/ingredients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tomato"))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(ingredientService, times(1)).findIngredientById(1L);
    }

    @Test
    public void testGetIngredientById_NotFound() throws Exception {
        when(ingredientService.findIngredientById(1L)).thenThrow(new EntityNotFoundException("Ingredient not found"));

        mockMvc.perform(get("/ingredients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Ingredient not found"));

        verify(ingredientService, times(1)).findIngredientById(1L);
    }

    @Test
    public void testCreateIngredient() throws Exception {
        Ingredient ingredient = new Ingredient(1L, "Carrot", 7, null);

        when(ingredientService.saveIngredient(any(Ingredient.class))).thenReturn(ingredient);

        mockMvc.perform(post("/ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Carrot\", \"quantity\": 7}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carrot"))
                .andExpect(jsonPath("$.quantity").value(7));

        verify(ingredientService, times(1)).saveIngredient(any(Ingredient.class));
    }

    @Test
    public void testUpdateIngredient() throws Exception {
        Ingredient updatedIngredient = new Ingredient(1L, "Carrot", 14, null);

        when(ingredientService.updateIngredient(anyLong(), any(Ingredient.class))).thenReturn(updatedIngredient);

        mockMvc.perform(put("/ingredients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Carrot\", \"quantity\": 7}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carrot"))
                .andExpect(jsonPath("$.quantity").value(14));

        verify(ingredientService, times(1)).updateIngredient(anyLong(), any(Ingredient.class));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        mockMvc.perform(delete("/ingredients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(ingredientService, times(1)).deleteIngredient(1L);
    }
}

