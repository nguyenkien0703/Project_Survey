package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.QuestionTypeDto;
import com.example.survey_tourist.dto.SurveyDto;
import com.example.survey_tourist.entity.QuestionType;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/question-type")
public class QuestionTypeResource {
    @Autowired
    private QuestionTypeService questionTypeService;


//    @GetMapping
//    public ResponseEntity<List<QuestionTypeDto>> findAllQuestionType(){
//        return ResponseEntity.ok(questionTypeService.findAll());
//    }
    @GetMapping(value = "/types")
    @CrossOrigin(value = "*",methods = RequestMethod.GET)
    public ResponseEntity<List<QuestionType>> findAllQuestion(){
        return ResponseEntity.ok(questionTypeService.findAllQT());
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuestionType> getQuestionType(@PathVariable final Integer id){
        return ResponseEntity.ok(questionTypeService.get(id));
    }
    @PostMapping
    public ResponseEntity<Integer> createQuestionType(  @Valid @RequestBody final QuestionTypeDto questionTypeDto) {
        return new ResponseEntity<>(questionTypeService.create(questionTypeDto), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerQuestionType(@Valid @RequestBody QuestionTypeDto questionTypeDto) throws Exception {
        QuestionType questionType = questionTypeService.registerQuestionType(questionTypeDto);
        System.out.println(questionTypeDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQuestionType(@PathVariable final  Integer id,@RequestBody @Valid  QuestionTypeDto questionTypeDto ){
        questionTypeService.update(id,questionTypeDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionType(@PathVariable final Integer id){

        questionTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
