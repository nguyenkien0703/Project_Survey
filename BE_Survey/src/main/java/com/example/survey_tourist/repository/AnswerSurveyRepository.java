package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.AnswerSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerSurveyRepository extends JpaRepository<AnswerSurvey, Integer> {
    int countBySurveyId(Integer surveyId);
}
