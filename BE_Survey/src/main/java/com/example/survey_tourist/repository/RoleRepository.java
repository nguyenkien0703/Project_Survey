package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepository extends JpaRepository<Role,Integer> {


}
