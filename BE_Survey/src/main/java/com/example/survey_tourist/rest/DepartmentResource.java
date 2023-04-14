package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.DepartmentDto;
import com.example.survey_tourist.entity.Department;
import com.example.survey_tourist.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentResource {

    @Autowired
    private DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAllSurveys(){
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable final Integer id){
        return ResponseEntity.ok(departmentService.get(id));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerDepartment(@Valid @RequestBody DepartmentDto departmentDto) throws Exception {
        Department department = departmentService.registerDepartment(departmentDto);
    }

    @PostMapping
    public ResponseEntity<Integer> createDepartment(@RequestBody @Valid final DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.create(departmentDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRole(@PathVariable final  Integer id,@RequestBody @Valid DepartmentDto departmentDto ){
        departmentService.update(id,departmentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable final Integer id){
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
