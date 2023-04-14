package com.example.survey_tourist.dto;

import com.example.survey_tourist.entity.status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDto implements Serializable {
    private int id;
    @NotNull
    @Size(min= 5, max = 100)
    private String nameSurvey;

    @NotNull
    @Size(min= 5, max = 100)
    private String contentSurvey;
    private status SaveOrDarft;
    private Boolean isDelete;
    private String  expiration;
    private Integer numberOfParticipants;
    private Integer progress;
    private List<QuestionSurveyDto> questionSurveys;
    public List<QuestionSurveyDto> getQuestions() {
        return questionSurveys;
    }
    public void setQuestions(List<QuestionSurveyDto> questions) {
        this.questionSurveys = questions;
    }
}
