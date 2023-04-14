package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionTypeRepository  extends JpaRepository<QuestionType,Integer> {

}
