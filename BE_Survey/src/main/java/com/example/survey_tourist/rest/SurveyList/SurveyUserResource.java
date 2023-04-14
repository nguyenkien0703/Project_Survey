package com.example.survey_tourist.rest.SurveyList;

import com.example.survey_tourist.dto.SurveyDto;
import com.example.survey_tourist.dto.UserDto;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.entity.SurveyUser;
import com.example.survey_tourist.entity.User;
import com.example.survey_tourist.service.SurveyService;
import com.example.survey_tourist.service.SurveyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
// get All user'survey

@RestController
@RequestMapping("/getSurveyUsers")
public class SurveyUserResource {
    @Autowired
    private SurveyUserService surveyUserService;
    @GetMapping("")
    public ResponseEntity<List<SurveyUser>> findAllSurveyUsers(){
        return ResponseEntity.ok(surveyUserService.findAllSurveyUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable final Integer id){
        return ResponseEntity.ok(surveyUserService.get(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Integer id){

        surveyUserService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
