package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.SurveyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser,Integer> {

}
