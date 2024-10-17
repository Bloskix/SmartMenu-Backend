package com.back.smartmenuapi.ingredient;

import com.back.smartmenuapi.meal.Meal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ingredient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer quantity;

    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private List<Meal> meals;

    public void validateQuantity() {
        if (this.quantity == null || this.quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}
