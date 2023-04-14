package com.example.survey_tourist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question_Type")
public class QuestionType  implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_Type",nullable = false)
    private String nameType;

    @Column(name = "is_Delete")
    private Boolean isDelete= false;

    @Column(name="create_At")
    private LocalDate createAt;

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


}
