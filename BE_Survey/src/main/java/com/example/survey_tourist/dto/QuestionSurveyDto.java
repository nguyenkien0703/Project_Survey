package com.example.survey_tourist.dto;

import com.example.survey_tourist.entity.QuestionType;
import com.example.survey_tourist.entity.Survey;
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
public class QuestionSurveyDto implements Serializable {
    private int id;

    @Size(max=100)
    private String nameQuestion;

    private Integer survey;
    private Integer questionType;
    private List<OptionQuestionDto> optionQuestionDtos;

    public List<OptionQuestionDto> getOptionQuestionDtos() {
        return optionQuestionDtos;
    }
    public void setQuestions(List<OptionQuestionDto> optionQuestionDtos) {
        this.optionQuestionDtos = optionQuestionDtos;
    }

}
