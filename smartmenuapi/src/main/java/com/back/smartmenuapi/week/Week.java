package com.back.smartmenuapi.week;

import com.back.smartmenuapi.day.Day;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "week")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_date", nullable = false, updatable = false)
    @Builder.Default
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "days")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Day> days;
}
