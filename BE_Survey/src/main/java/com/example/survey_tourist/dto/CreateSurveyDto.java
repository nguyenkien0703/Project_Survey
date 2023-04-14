package com.example.survey_tourist.dto;

import com.example.survey_tourist.entity.status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSurveyDto implements Serializable {

    private Integer id ;//
    private String nameSurveyCreateSurvey;//
    private String contentSurveyCreateSurvey;//
    private String nameQuestionCreateSurvey;//
    private Integer questionTypeIdCreateSurvey;
    private String nameOptionCreateSurvey;

}
