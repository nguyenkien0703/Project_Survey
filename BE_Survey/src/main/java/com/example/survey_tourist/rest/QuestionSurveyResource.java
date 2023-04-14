package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.QuestionSurveyDto;
import com.example.survey_tourist.dto.SurveyDto;
import com.example.survey_tourist.entity.QuestionSurvey;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.service.QuestionSurveyService;
import com.example.survey_tourist.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/question-survey")
public class QuestionSurveyResource {

    @Autowired
    private QuestionSurveyService questionSurveyService;

    @GetMapping
    public ResponseEntity<List<QuestionSurveyDto>> findAllSurveys(){
        return ResponseEntity.ok(questionSurveyService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuestionSurvey> getSurvey(@PathVariable final Integer id){
        return ResponseEntity.ok(questionSurveyService.get(id));
    }
    @PostMapping
    public ResponseEntity<Integer> createQuestionSurvey(@RequestBody @Valid final QuestionSurveyDto questionSurveyDto) {
        return new ResponseEntity<>(questionSurveyService.create(questionSurveyDto), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerQuestionSurvey(@Valid @RequestBody QuestionSurveyDto questionSurveyDto) throws Exception {
        QuestionSurvey questionSurvey = questionSurveyService.registerQuestionSurvey(questionSurveyDto);
        System.out.println(questionSurveyDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQuestionSurvey(@PathVariable final  Integer id,@RequestBody @Valid QuestionSurveyDto questionSurveyDto ){
        questionSurveyService.update(id,questionSurveyDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionSurvey(@PathVariable final Integer id){

        questionSurveyService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
