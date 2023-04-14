package com.example.survey_tourist.service;

import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.entity.SurveyUser;
import com.example.survey_tourist.repository.SurveyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SurveyUserService {
    @Autowired
    private SurveyUserRepository surveyUserRepository;
    public List<SurveyUser> findAllSurveyUser(){
        return surveyUserRepository.findAll();

    }
    public Survey get(final Integer id) {
        return surveyUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getSurveyId();
    }
    public void delete(final Integer id ){
        final SurveyUser surveyUser = surveyUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate Date = LocalDate.now() ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String  deleteAt = Date.format(formatter);
        surveyUser.setIsDelete(true);
        surveyUserRepository.save(surveyUser);
    }


}
