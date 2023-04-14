package com.example.survey_tourist.rest.user;
import com.example.survey_tourist.dto.SurveyDtoList;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/survey-user")
public class SurveyUserRenderResource {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/surveys")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public ResponseEntity<Page<SurveyDtoList>> getAllSurveys(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<SurveyDtoList> surveys = surveyService.getAll(name,pageNumber, pageSize);
        return ResponseEntity.ok(surveys);
    }
    @GetMapping("/list-data/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable final Integer id) {
        return ResponseEntity.ok(surveyService.get(id));
    }
    @GetMapping("/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public ResponseEntity<Survey> getSurveyById(@PathVariable Integer id) {
        Optional<Survey> surveyOptional = surveyService.getSurveyWithQuestionsById(id);
        if (surveyOptional.isPresent()) {
            return ResponseEntity.ok(surveyOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
