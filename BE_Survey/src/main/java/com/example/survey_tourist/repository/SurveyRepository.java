package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface SurveyRepository extends JpaRepository<Survey, Integer>, PagingAndSortingRepository<Survey, Integer> {
//    @Query(value = "SELECT  s  FROM Survey s ORDER BY s.createAt ASC LIMIT 1",nativeQuery = true)
//    Optional<Survey> findSurveyFirstCreateAtAsc();

    Page<Survey> findAll(Specification<Survey> spec, Pageable pageable);


    @Query("SELECT COUNT(su) FROM SurveyUser su WHERE su.SurveyId.id = :surveyId")
    Integer countParticipantsBySurvey(@Param("surveyId") Integer surveyId);

    @Query("SELECT COUNT(ans) FROM AnswerSurvey ans WHERE ans.surveyId.id = :surveyId")
    Integer countAnswersBySurvey(@Param("surveyId") Integer surveyId);


}
