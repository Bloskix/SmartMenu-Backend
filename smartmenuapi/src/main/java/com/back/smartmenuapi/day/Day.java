package com.back.smartmenuapi.day;

import com.back.smartmenuapi.meal.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dailyMeal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date;

    @OneToOne(cascade = CascadeType.ALL)
    private Meal meal;
}
