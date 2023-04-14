package com.example.survey_tourist.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "survey")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Survey implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name_Survey",nullable = false)
    private String nameSurvey;
    @Column(name="content_Survey",nullable = false)
    private String contentSurvey;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(length = 20)
    private status SaveOrDarft = status.inittial;

    @Column(name="expiration")

    private LocalDate  expiration;

    @Column(name="is_Delete",nullable = false)
    private Boolean isDelete = false;

    @Column(name="create_At")
    private LocalDate createAt;

    @OneToMany(mappedBy = "SurveyId")
    List<SurveyUser> surveyUsers;



    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<QuestionSurvey> questionSurveys = new ArrayList<>();



    @Column(name="update_At")
    private LocalDate updateAt;

    @Column(name="delete_At")
    private LocalDate deleteAt;
    @Column(name="create_By")
    private String createBy;

    @Column(name="update_By")
    private String updateBy;


    @Column(name="delete_By")
    private String deleteBy;


    public void addQuestionSurvey(QuestionSurvey questionSurvey) {
        this.questionSurveys.add(questionSurvey);
        questionSurvey.setSurvey(this);
    }

}
