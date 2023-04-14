package com.example.survey_tourist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="answer_Survey")
public class AnswerSurvey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content_Answer_Survey")
    private String contentAnswerSurvey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id", nullable = false)
    private User userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_Id", nullable = false)
    private Survey surveyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_Id", nullable = false)
    private QuestionSurvey question_Survey;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_Question_Survey_Id")
    private OptionQuestionSurvey optionQuestionSurvey;

    @Column(name = "is_Delete")
    private Boolean isDelete= false;
}
