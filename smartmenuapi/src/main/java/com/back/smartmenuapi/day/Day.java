/*
package com.back.smartmenuapi.day;

import com.back.smartmenuapi.meal.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "day")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dayNumber; // Validaremos que esté entre 1 y 7

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals;

    public void setDayNumber(int dayNumber) {
        if(dayNumber < 1 || dayNumber > 7) {
            throw new IllegalArgumentException("Day number must be between 1 and 7.");
        }
        this.dayNumber = dayNumber;
    }

    // Método para reemplazar una comida por tipo
    public void replaceMeal(Meal oldMeal, Meal newMeal) {
        int index = meals.indexOf(oldMeal);
        if (index != -1) {
            meals.set(index, newMeal);
        } else {
            throw new IllegalArgumentException("The meal to replace was not found.");
        }
    }
}
*/
