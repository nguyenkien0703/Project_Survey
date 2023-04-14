package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.QuestionSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSurveyRepository  extends JpaRepository<QuestionSurvey,Integer> {




}
