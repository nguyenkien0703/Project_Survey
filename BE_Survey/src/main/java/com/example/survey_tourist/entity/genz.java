package com.example.survey_tourist.entity;

public enum genz {
   MALE(0), FEMALE(1);

   private final int value;


   genz(int value) {
      this.value = value;
   }

   public int getValue() {
      return value;
   }
}
