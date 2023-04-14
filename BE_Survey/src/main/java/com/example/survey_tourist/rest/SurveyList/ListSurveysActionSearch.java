package com.example.survey_tourist.rest.SurveyList;

import com.example.survey_tourist.dto.SurveyDtoList;
import com.example.survey_tourist.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ListSurveysActionSearch {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/getSurveys")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> searchSurvey(
            @RequestParam(required = false, defaultValue = "") String nameSurvey,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<SurveyDtoList> surveysPage = surveyService.getAllSurveyResearch(nameSurvey, startDate, endDate, pageNumber, pageSize);
        Long countSurvey = surveyService.countSurveyInfoDto(nameSurvey, startDate, endDate);
        Map<String, Object> response = new HashMap<>();
        response.put("data", surveysPage);
        response.put("total", countSurvey);
        return ResponseEntity.ok(response);
    }

}
