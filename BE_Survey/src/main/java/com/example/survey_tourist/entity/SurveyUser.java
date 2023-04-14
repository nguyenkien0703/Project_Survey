package com.example.survey_tourist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="survey_User",uniqueConstraints = {@UniqueConstraint(columnNames = {"user_Id", "survey_Id"})} )
public class SurveyUser implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id", nullable = false)
    private User  UserId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_Id",nullable = false)
    private Survey  SurveyId;

    @Column(name = "is_Delete",nullable = false)
    private Boolean isDelete = false;



}
