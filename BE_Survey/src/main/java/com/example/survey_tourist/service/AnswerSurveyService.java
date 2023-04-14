package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.AnswerSurveyDto;
import com.example.survey_tourist.entity.AnswerSurvey;
import com.example.survey_tourist.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AnswerSurveyService {
    @Autowired
    private AnswerSurveyRepository  answerSurveyRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OptionQuestionSurveyRepository optionQuestionSurveyRepository;
    @Autowired
    private QuestionSurveyRepository questionSurveyRepository;
    public AnswerSurvey addAnswerSurvey(AnswerSurveyDto answerSurveyDto) throws Exception {
        AnswerSurvey answerSurvey = new AnswerSurvey();
        answerSurvey.setContentAnswerSurvey(answerSurveyDto.getContentAnswerSurvey());
        answerSurvey.setSurveyId(surveyRepository.findById(answerSurveyDto.getSurveyId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        answerSurvey.setUserId(userRepository.findById(answerSurveyDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        if(answerSurveyDto.getOptionQuestionSurvey()!=null){
            answerSurvey.setOptionQuestionSurvey(optionQuestionSurveyRepository.findById(answerSurveyDto.getOptionQuestionSurvey()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        }else {
            answerSurvey.setOptionQuestionSurvey(null);
        }
        answerSurvey.setQuestion_Survey(questionSurveyRepository.findById(answerSurveyDto.getQuestion_Survey()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        answerSurveyRepository.save(answerSurvey);
        answerSurvey.setIsDelete(false);
        return answerSurvey;
    }
    public void save(AnswerSurvey newAnswerSurvey) {
        answerSurveyRepository.save(newAnswerSurvey);
    }
}
