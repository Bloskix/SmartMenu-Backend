/*
package com.back.smartmenuapi.day;

import com.back.smartmenuapi.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/days")
public class DayController {

    @Autowired
    private DayService dayService;

    @GetMapping("/{id}")
    public Day getDayById(@PathVariable Long id) {
        return dayService.findDayById(id);
    }

    @GetMapping
    public List<Day> getAllDays() {
        return dayService.findAllDays();
    }

    @PostMapping
    public Day createDay(@RequestBody Day day) {
        return dayService.createDay(day);
    }

    @PutMapping("/{id}")
    public Day updateDay(@PathVariable Long id, @RequestBody Day day) {
        return dayService.updateDay(id, day);
    }

    @DeleteMapping("/{id}")
    public void deleteDay(@PathVariable Long id) {
        dayService.deleteDay(id);
    }

    @PutMapping("/{dayId}/replaceMeal")
    public void replaceMealInDay(
            @PathVariable Long dayId,
            @RequestBody ReplaceMealRequest replaceMealRequest) {
        Meal oldMeal = replaceMealRequest.getOldMeal();
        Meal newMeal = replaceMealRequest.getNewMeal();
        dayService.replaceMealInDay(dayId, oldMeal, newMeal);
    }
}
*/
