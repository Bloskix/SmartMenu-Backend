package com.back.smartmenuapi.day;


import com.back.smartmenuapi.error.NotFoundException;
import com.back.smartmenuapi.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class DayServiceImplement implements DayService{

    @Autowired
    DayRepository dayRepository;
    @Autowired
    MealService mealService;

    @Override
    public Day updateDay(Long id, Day day) throws NotFoundException {
        Optional<Day> dayOptional = dayRepository.findById(id);
        if (dayOptional.isEmpty()) {
            throw new NotFoundException("Day not found in database");
        }
        if (Objects.nonNull(day.getDate())) {
            day.setDate(day.getDate());
        }
        if (Objects.nonNull(day.getMeal())) {
            day.setMeal(day.getMeal());
        }
        return dayRepository.save(day);
    }
}
