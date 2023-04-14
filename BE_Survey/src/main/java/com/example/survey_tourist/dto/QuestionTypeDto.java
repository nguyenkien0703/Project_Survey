package com.example.survey_tourist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class QuestionTypeDto implements Serializable {
    private int id;

    @NotNull
    @Size(max = 100)
    private String nameType;




}
