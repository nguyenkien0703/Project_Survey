package com.example.survey_tourist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto implements Serializable {

    private int id;

    @NotBlank
    @Size(max = 20)
    private String  nameDepartment;



}
