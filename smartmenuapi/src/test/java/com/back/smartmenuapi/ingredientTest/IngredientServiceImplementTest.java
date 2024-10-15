package com.back.smartmenuapi.ingredientTest;

import com.back.smartmenuapi.ingredient.Ingredient;
import com.back.smartmenuapi.ingredient.IngredientRepository;
import com.back.smartmenuapi.ingredient.IngredientServiceImplement;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngredientServiceImplementTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientServiceImplement ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveIngredient_NewIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Tomato");
        ingredient.setQuantity(5);

        when(ingredientRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);

        assertNotNull(savedIngredient);
        assertEquals("Tomato", savedIngredient.getName());
        assertEquals(5, savedIngredient.getQuantity());
        verify(ingredientRepository, times(1)).save(ingredient);
    }

    @Test
    void testSaveIngredient_ExistingIngredient() {
        Ingredient existingIngredient = new Ingredient();
        existingIngredient.setName("Tomato");
        existingIngredient.setQuantity(5);

        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("Tomato");
        newIngredient.setQuantity(3);

        when(ingredientRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.of(existingIngredient));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(existingIngredient);

        Ingredient savedIngredient = ingredientService.saveIngredient(newIngredient);

        assertEquals(8, savedIngredient.getQuantity());
        verify(ingredientRepository, times(1)).save(existingIngredient);
    }

    @Test
    void testFindIngredientById_IngredientFound() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Onion");

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));

        Ingredient foundIngredient = ingredientService.findIngredientById(1L);

        assertNotNull(foundIngredient);
        assertEquals("Onion", foundIngredient.getName());
        verify(ingredientRepository, times(1)).findById(1L);
    }

    @Test
    void testFindIngredientById_IngredientNotFound() {
        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            ingredientService.findIngredientById(1L);
        });

        assertEquals("Ingredient not found", exception.getMessage());
        verify(ingredientRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateIngredient() {
        Ingredient existingIngredient = new Ingredient();
        existingIngredient.setId(1L);
        existingIngredient.setName("Potato");
        existingIngredient.setQuantity(4);

        Ingredient updatedIngredient = new Ingredient();
        updatedIngredient.setName("Potato");
        updatedIngredient.setQuantity(6);

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(existingIngredient));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(existingIngredient);

        Ingredient result = ingredientService.updateIngredient(1L, updatedIngredient);

        assertEquals(6, result.getQuantity());
        verify(ingredientRepository, times(1)).save(existingIngredient);
    }

    @Test
    void testDeleteIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Lettuce");

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));

        ingredientService.deleteIngredient(1L);

        verify(ingredientRepository, times(1)).delete(ingredient);
    }
}

