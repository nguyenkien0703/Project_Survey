package com.example.survey_tourist.entity;

public enum status {
    inittial(0), draft(1),save(2);


    private final int  value;

    status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
