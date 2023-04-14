package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.OptionQuestionDto;
import com.example.survey_tourist.entity.OptionQuestionSurvey;
import com.example.survey_tourist.service.OptionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/option-question-survey")
public class OptionQuestionSurveyResource {
    @Autowired
    private OptionQuestionService optionQuestionService;


    @GetMapping
    public ResponseEntity<List<OptionQuestionDto>> findAllOptionQuestion(){
        return ResponseEntity.ok(optionQuestionService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OptionQuestionSurvey> getOptionQuestion(@PathVariable final Integer id){
        return ResponseEntity.ok(optionQuestionService.get(id));
    }
    @PostMapping
    public ResponseEntity<Integer> createOptionQuestion(  @Valid @RequestBody final OptionQuestionDto optionQuestionDto) {
        return new ResponseEntity<>(optionQuestionService.create(optionQuestionDto), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerOptionQuestion(@Valid @RequestBody OptionQuestionDto optionQuestionDto) throws Exception {
        OptionQuestionSurvey optionQuestionSurvey = optionQuestionService.registerOptionQuestionSurvey(optionQuestionDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOptionQuestion(@PathVariable final  Integer id,@RequestBody @Valid  OptionQuestionDto optionQuestionDto ){
        optionQuestionService.update(id,optionQuestionDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOptionQuestion(@PathVariable final Integer id){

        optionQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
