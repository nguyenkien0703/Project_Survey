package com.example.survey_tourist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "userr")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_Account")
    private String nameAccount;


    @Column(name = "password_Account", nullable = false)
    private String passwordAccount;


    private String salt;

    @Column(name = "user_Name",nullable = false,unique = true)
    private String userName;

    @Column(name = "phone_Number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 20)
    private genz eSex;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_Id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_Id")
    private Department department;


    @Column(name = "is_Delete")
    private Boolean isDelete= false;
    @OneToMany(mappedBy = "UserId")
    List<SurveyUser> surveyUsers;



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
    private String deleteBy;

    // function create salt random for every user
    private String saltPassword(String passwordAccount) {
        String saltedPassword = passwordAccount + "{" + salt + "}";
        return BCrypt.hashpw(saltedPassword, BCrypt.gensalt());
    }

}
