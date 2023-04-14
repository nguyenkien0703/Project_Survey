package com.example.survey_tourist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OptionQuestionDto implements Serializable {
    private int id;
    @Size(max = 100)
    private String nameOption;
    private Integer questionSurveyId;

}
