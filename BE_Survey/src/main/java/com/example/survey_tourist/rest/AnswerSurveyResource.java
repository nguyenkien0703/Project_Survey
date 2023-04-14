package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.AnswerSurveyDto;
import com.example.survey_tourist.entity.AnswerSurvey;
import com.example.survey_tourist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/answer-survey")
public class AnswerSurveyResource {
    @Autowired
    private AnswerSurveyService answerSurveyService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private UserService userService;
    @Autowired
    private OptionQuestionSurveyService optionQuestionSurveyService;
    @Autowired
    private QuestionSurveyService questionSurveyService;
    @PostMapping(value = "/create-answer-survey")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnswerSurvey(@Valid @RequestBody AnswerSurveyDto answerSurveyDto) throws Exception {
        AnswerSurvey answerSurvey = answerSurveyService.addAnswerSurvey(answerSurveyDto);
    }

    @PostMapping("/answer-surveys")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createAnswerSurvey(@RequestBody List<AnswerSurveyDto> answerSurveys) {
        try {
            for (AnswerSurveyDto answerSurvey : answerSurveys) {
                AnswerSurvey newAnswerSurvey = new AnswerSurvey();
                newAnswerSurvey.setContentAnswerSurvey(answerSurvey.getContentAnswerSurvey());
                newAnswerSurvey.setSurveyId(surveyService.getById(answerSurvey.getSurveyId()));
                newAnswerSurvey.setUserId(userService.get(answerSurvey.getUserId()));
                if(answerSurvey.getOptionQuestionSurvey()!=null){
                    newAnswerSurvey.setOptionQuestionSurvey(optionQuestionSurveyService.getById(answerSurvey.getOptionQuestionSurvey()));
                }else {
                    newAnswerSurvey.setOptionQuestionSurvey(null);
                }
                newAnswerSurvey.setQuestion_Survey(questionSurveyService.getById(answerSurvey.getQuestion_Survey()));
                newAnswerSurvey.setIsDelete(answerSurvey.isDelete());
                answerSurveyService.save(newAnswerSurvey);
            }
            return new ResponseEntity<>("AnswerSurveys have been created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create AnswerSurveys.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
