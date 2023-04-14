package com.example.survey_tourist.service;

import com.example.survey_tourist.entity.OptionQuestionSurvey;
import com.example.survey_tourist.repository.OptionQuestionSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class OptionQuestionSurveyService {

    @Autowired
    private OptionQuestionSurveyRepository optionQuestionSurveyRepository;

    public OptionQuestionSurvey getById(final Integer id) {
        return optionQuestionSurveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
