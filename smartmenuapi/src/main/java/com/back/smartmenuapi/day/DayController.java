package com.back.smartmenuapi.day;

import com.back.smartmenuapi.error.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DayController {

    DayService dayService;

    @PutMapping("/updateDay/{id}")
    public Day updateDay(@PathVariable Long id, @RequestBody Day day) throws NotFoundException {
        return dayService.updateDay(id, day);
    }
}
