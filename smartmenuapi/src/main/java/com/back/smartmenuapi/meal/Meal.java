//Este archivo es una entidad que representa la tabla de alimentos en la base de datos, y se mapea a través de JPA

package com.back.smartmenuapi.meal;

import com.back.smartmenuapi.ingredient.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "meal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @Column(name = "prep_time")
    private Integer prepTime;
}
