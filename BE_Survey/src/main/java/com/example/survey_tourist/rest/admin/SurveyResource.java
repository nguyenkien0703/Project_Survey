package com.example.survey_tourist.rest.admin;

import com.example.survey_tourist.dto.SurveyDto;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyResource {

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public ResponseEntity<List<SurveyDto>> findAllSurveys(){
        return ResponseEntity.ok(surveyService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable final Integer id){
        return ResponseEntity.ok(surveyService.get(id));
    }
    @PostMapping
    public ResponseEntity<Integer> createSurvey(@RequestBody @Valid final SurveyDto surveyDto) {
        return new ResponseEntity<>(surveyService.create(surveyDto), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerSurvey(@Valid @RequestBody SurveyDto surveyDto) throws Exception {
        Survey survey = surveyService.registerSurvey(surveyDto);
        System.out.println(surveyDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSurvey(@PathVariable final  Integer id,@RequestBody @Valid SurveyDto surveyDto ){
        surveyService.update(id,surveyDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @CrossOrigin(value = "*",methods = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSurvey(@PathVariable final Integer id){
        surveyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
