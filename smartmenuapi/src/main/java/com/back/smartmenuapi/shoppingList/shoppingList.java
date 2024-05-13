package com.back.smartmenuapi.shoppingList;

import com.back.smartmenuapi.day.Day;
import com.back.smartmenuapi.ingredient.Ingredient;
import com.back.smartmenuapi.meal.Meal;
import com.back.smartmenuapi.week.Week;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class shoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ingredients_to_buy")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredientsToBuy;

    public List<Ingredient> getIngredientsToBuy(Week week) {
        List<Ingredient> ingredientsToBuy = new ArrayList<>();
        for (Day day : week.getDays()) {
            Meal meal = day.getMeal();
            ingredientsToBuy.addAll(meal.getIngredients());
            //Cuando haya mas de una comida por dia este codigo se descomenta
//            for (Meal meal : day.getMeal()) {
//                ingredientsToBuy.addAll(meal.getIngredients());
//            }
        } return ingredientsToBuy;
    }
}

