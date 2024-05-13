package com.back.smartmenuapi.day;

import com.back.smartmenuapi.meal.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "date", nullable = false, updatable = false)
    @Builder.Default
    private LocalDate date = LocalDate.now();

    @Column(name = "meal")
    @OneToOne(cascade = CascadeType.ALL)
    private Meal meal;
}
