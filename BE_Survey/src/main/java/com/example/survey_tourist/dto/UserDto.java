package com.example.survey_tourist.dto;


import com.example.survey_tourist.entity.genz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private int id;
    private String userName;
    private String passwordAccount;
    private String checkPass;
    private String email;
    private String CheckPass;
    private String nameAccount;
    private genz eSex;
    private String phoneNumber;
    private String address;
    private Integer departmentId ;
    private Integer roleId;
    private String roleName;
}
