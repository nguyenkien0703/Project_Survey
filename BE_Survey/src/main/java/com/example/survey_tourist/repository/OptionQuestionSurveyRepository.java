package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.OptionQuestionSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionQuestionSurveyRepository  extends JpaRepository<OptionQuestionSurvey,Integer> {
}
