package com.back.smartmenuapi.shoppingList;

import com.back.smartmenuapi.ingredient.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
