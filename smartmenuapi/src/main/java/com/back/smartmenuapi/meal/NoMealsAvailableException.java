package com.back.smartmenuapi.meal;

public class NoMealsAvailableException extends RuntimeException {
    public NoMealsAvailableException(String message) {
        super(message);
    }
}
