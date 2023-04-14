package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.DepartmentDto;
import com.example.survey_tourist.dto.QuestionSurveyDto;
import com.example.survey_tourist.entity.Department;
import com.example.survey_tourist.entity.QuestionSurvey;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.repository.QuestionSurveyRepository;
import com.example.survey_tourist.repository.QuestionTypeRepository;
import com.example.survey_tourist.repository.SurveyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionSurveyService {
    @Autowired
    private QuestionSurveyRepository questionSurveyRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    public List<QuestionSurveyDto> findAll(){
        return questionSurveyRepository.findAll().stream().map(questionSurvey -> mapToDTO(questionSurvey, new QuestionSurveyDto())).collect(Collectors.toList());
    }


    public QuestionSurvey getById(final Integer id) {
        return questionSurveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public QuestionSurvey get(final Integer id) {
        return questionSurveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public Integer create(final QuestionSurveyDto questionSurveyDto) {
        final QuestionSurvey questionSurvey = new QuestionSurvey();
        mapToEntity(questionSurveyDto, questionSurvey);
        return questionSurveyRepository.save(questionSurvey).getId();
    }

    public void update(final Integer id, final QuestionSurveyDto questionSurveyDto){
        final QuestionSurvey questionSurvey = questionSurveyRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        questionSurvey.setNameQuestion(questionSurveyDto.getNameQuestion());
        questionSurvey.setSurvey(surveyRepository.findById(questionSurveyDto.getSurvey()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        questionSurvey.setQuestionType(questionTypeRepository.findById(questionSurveyDto.getQuestionType()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        LocalDate Date = LocalDate.now() ;
        questionSurvey.setUpdateAt(Date);
        questionSurveyRepository.save(questionSurvey);
    }

    public QuestionSurvey registerQuestionSurvey(QuestionSurveyDto  questionSurveyDto) throws Exception {
        QuestionSurvey questionSurvey = new QuestionSurvey();
        questionSurvey.setNameQuestion(questionSurveyDto.getNameQuestion());
        questionSurvey.setSurvey(surveyRepository.findById(questionSurveyDto.getSurvey()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        questionSurvey.setQuestionType(questionTypeRepository.findById(questionSurveyDto.getQuestionType()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        LocalDate now = LocalDate.now();
        questionSurvey.setCreateAt(now);
        questionSurvey.setUpdateAt(now);

        questionSurveyRepository.save(questionSurvey);
        return questionSurvey;
    }



    public void  delete(final Integer id){
        final QuestionSurvey questionSurvey = questionSurveyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate  deleteAt = LocalDate.now();
        questionSurvey.setIsDelete(true);
        questionSurveyRepository.save(questionSurvey);
    }

    private QuestionSurvey mapToEntity(final QuestionSurveyDto questionSurveyDto, QuestionSurvey questionSurvey ){
        questionSurvey = modelMapper.map(questionSurveyDto,QuestionSurvey.class);
        return questionSurvey ;
    }
    private QuestionSurveyDto mapToDTO(final QuestionSurvey questionSurvey, QuestionSurveyDto questionSurveyDto){
        questionSurveyDto = modelMapper.map(questionSurvey,QuestionSurveyDto.class);
        return questionSurveyDto;
    }


    public QuestionSurvey save(QuestionSurvey questionSurvey) {
        return questionSurveyRepository.save(questionSurvey);
    }

}
