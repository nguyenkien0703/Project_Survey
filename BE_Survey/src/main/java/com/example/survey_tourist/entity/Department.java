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
@Table(name="department")
public class Department implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name_Department",nullable = false)
    private String nameDepartment;

    @Column(name = "is_Delete",nullable = false)

    private Boolean isDelete = false;


}
