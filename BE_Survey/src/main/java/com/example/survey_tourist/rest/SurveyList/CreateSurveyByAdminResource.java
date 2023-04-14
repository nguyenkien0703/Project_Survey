package com.example.survey_tourist.rest.SurveyList;

import com.example.survey_tourist.dto.*;
import com.example.survey_tourist.entity.OptionQuestionSurvey;
import com.example.survey_tourist.entity.QuestionSurvey;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.repository.OptionQuestionSurveyRepository;
import com.example.survey_tourist.service.QuestionSurveyService;
import com.example.survey_tourist.service.QuestionTypeService;
import com.example.survey_tourist.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(value = "/createSurvey")
@RestController
public class CreateSurveyByAdminResource {
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private QuestionTypeService questionTypeService;
    @Autowired
    private QuestionSurveyService questionSurveyService;
    @Autowired
    private OptionQuestionSurveyRepository optionQuestionSurveyRepository;
    @PostMapping("/surveys")
    public ResponseEntity<Survey> createSurvey(@RequestBody SurveyDto surveyDto) {
        Survey survey = new Survey();
        survey.setNameSurvey(surveyDto.getNameSurvey());
        survey.setContentSurvey(surveyDto.getContentSurvey());
        survey = surveyService.save(survey);
        List<QuestionSurveyDto> questionSurveyDtos = surveyDto.getQuestions();
        for (QuestionSurveyDto questionDto : questionSurveyDtos) {
            QuestionSurvey question = new QuestionSurvey();
            question.setNameQuestion(questionDto.getNameQuestion());
            question.setQuestionType(questionTypeService.get(questionDto.getQuestionType()));
            question.setSurvey(survey);
            question = questionSurveyService.save(question);
            List<OptionQuestionDto> optionQuestionDtos = questionDto.getOptionQuestionDtos();
            for (OptionQuestionDto optionDto :optionQuestionDtos) {
                OptionQuestionSurvey option = new OptionQuestionSurvey();
                option.setNameOption(optionDto.getNameOption());
                option.setQuestionSurvey(question);
                System.out.println(question);
                optionQuestionSurveyRepository.save(option);
                System.out.println(optionDto.getNameOption());
            }
        }
        return ResponseEntity.ok(survey);
    }

}
