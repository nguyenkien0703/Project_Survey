package com.example.survey_tourist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question_Survey")
public class QuestionSurvey implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name_Question",nullable = false)
    private String nameQuestion;


    @Column(name = "require_Question")
    private Boolean requireQuestion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_Id",nullable = false)
    private Survey survey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_Type_Id",nullable = false)
    private QuestionType questionType;


    @OneToMany(mappedBy = "questionSurvey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionQuestionSurvey> optionQuestions ;


    @Column(name = "is_Delete")
    private Boolean isDelete= false;

    @Column(name = "create_At")
    private LocalDate createAt;

    @Column(name = "update_At")
    private LocalDate updateAt;

    @Column(name = "delete_At")
    private LocalDate deleteAt;
    @Column(name = "create_By")
    private String createBy;

    @Column(name = "update_By")
    private String updateBy;


    @Column(name = "delete_By")
    private String deleteBy ;


    public void addOptionQuestion(OptionQuestionSurvey optionQuestion) {
        this.optionQuestions.add(optionQuestion);
        optionQuestion.setQuestionSurvey(this);
    }
}