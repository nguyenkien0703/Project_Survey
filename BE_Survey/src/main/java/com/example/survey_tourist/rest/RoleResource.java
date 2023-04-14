package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.RoleDto;
import com.example.survey_tourist.entity.Role;
import com.example.survey_tourist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/role")
@RestController

public class RoleResource {
    @Autowired
    private RoleService roleService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerRole(@Valid @RequestBody RoleDto roleDto) throws Exception {
        Role role = roleService.registerRole(roleDto);
        System.out.println(roleDto);
    }


}
