package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DepartmentRepository  extends JpaRepository<Department,Integer> {



}
