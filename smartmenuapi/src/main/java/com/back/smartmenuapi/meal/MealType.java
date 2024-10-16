package com.back.smartmenuapi.meal;

public enum MealType {
    MEAL(1),
    SNACK(2);

    private final int value;

    MealType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MealType fromValue(int value) {
        for (MealType type : MealType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MealType value: " + value);
    }
}
