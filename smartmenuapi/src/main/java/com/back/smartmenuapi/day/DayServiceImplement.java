/*package com.back.smartmenuapi.day;

import com.back.smartmenuapi.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayServiceImplement implements DayService {

    @Autowired
    private DayRepository dayRepository;

    @Override
    public Day findDayById(Long id) {
        return dayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Day not found."));
    }

    @Override
    public List<Day> findAllDays() {
        return dayRepository.findAll();
    }

    @Override
    public Day createDay(Day day) {
        return dayRepository.save(day);
    }

    @Override
    public Day updateDay(Long id, Day updatedDay) {
        Day day = findDayById(id);
        day.setDayNumber(updatedDay.getDayNumber());
        day.setMeals(updatedDay.getMeals());
        return dayRepository.save(day);
    }

    @Override
    public void deleteDay(Long id) {
        dayRepository.deleteById(id);
    }

    @Override
    public void replaceMealInDay(Long dayId, Meal oldMeal, Meal newMeal) {
        Day day = findDayById(dayId);
        day.replaceMeal(oldMeal, newMeal);
        dayRepository.save(day);
    }
}*/
