package com.example.survey_tourist.rest.admin;

import com.example.survey_tourist.dto.RoleDto;
import com.example.survey_tourist.dto.SurveyDto;
import com.example.survey_tourist.entity.Role;
import com.example.survey_tourist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")

public class RolesResource {
    @Autowired
    private RoleService roleService;
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable final Integer id){
        return ResponseEntity.ok(roleService.get(id));
    }
    @GetMapping
    public ResponseEntity<List<RoleDto>> findAllRole(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/getRoles")// get all user'nameRole
    public ResponseEntity<List<String>> getNameRoles(){
        return ResponseEntity.ok(roleService.findAllNameRoles());
    }
    @PostMapping
    public ResponseEntity<Integer> createRole(@RequestBody @Valid final RoleDto roleDto) {
        return new ResponseEntity<>(roleService.create(roleDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRole(@PathVariable final  Integer id,@RequestBody @Valid RoleDto roleDto ){
        roleService.update(id,roleDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable final Integer id){
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
