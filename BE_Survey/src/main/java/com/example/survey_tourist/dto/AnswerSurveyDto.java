package com.example.survey_tourist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSurveyDto {
    private int id;
    private String contentAnswerSurvey;
    private Integer userId;
    private Integer surveyId;
    private Integer question_Survey;
    private Integer optionQuestionSurvey;
    private boolean isDelete;
}
