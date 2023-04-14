package com.example.survey_tourist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="option_Question_Survey")
public class OptionQuestionSurvey implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int  id;

    @Column(name = "name_Option",nullable = false)
    private String nameOption;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_Survey_Id", nullable = false)
    private QuestionSurvey questionSurvey;



    @Column(name = "is_Delete")
    private Boolean isDelete= false;
}
