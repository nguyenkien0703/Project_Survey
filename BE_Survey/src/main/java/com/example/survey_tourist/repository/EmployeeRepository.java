package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<User, Integer> {
    Page<User> findAll(Specification<User> spec, Pageable pageable);
}
