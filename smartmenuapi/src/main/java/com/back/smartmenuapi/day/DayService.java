package com.back.smartmenuapi.day;

import com.back.smartmenuapi.error.NotFoundException;

public interface DayService {

    Day updateDay(Long id, Day day) throws NotFoundException;
}
