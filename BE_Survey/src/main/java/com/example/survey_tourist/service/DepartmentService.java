package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.DepartmentDto;
import com.example.survey_tourist.entity.Department;
import com.example.survey_tourist.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<DepartmentDto> findAll(){
        return departmentRepository.findAll().stream().map(department -> mapToDTO(department, new DepartmentDto())).collect(Collectors.toList());
    }
    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }
    public List<String> findAllNameRoles(){
        return departmentRepository.findAll().stream().map(Department::getNameDepartment).collect(Collectors.toList());
    }

    public Department get(final Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public Integer create(final DepartmentDto departmentDto) {
        final Department department = new Department();
        mapToEntity(departmentDto, department);
        return departmentRepository.save(department).getId();
    }

    public void update(final Integer id, final DepartmentDto departmentDto){
        final Department department = departmentRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        department.setNameDepartment(departmentDto.getNameDepartment());
//        department.setIsDelete(departmentDto.getIsDelete());
        departmentRepository.save(department);
    }

    public Department registerDepartment(DepartmentDto departmentDto) throws Exception {
        Department department = new Department();
        department.setNameDepartment(departmentDto.getNameDepartment());
//        department.setIsDelete(departmentDto.getIsDelete());
        departmentRepository.save(department);
        return department;
    }



    public void  delete(final Integer id){
        final Department department = departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        department.setIsDelete(false);
        departmentRepository.save(department);
    }

    private Department mapToEntity(final DepartmentDto departmentDto, Department department ){
        department = modelMapper.map(departmentDto,Department.class);
        return department ;
    }
    private DepartmentDto mapToDTO(final Department department, DepartmentDto departmentDto){
        departmentDto = modelMapper.map(department,DepartmentDto.class);
        return departmentDto;
    }










}
