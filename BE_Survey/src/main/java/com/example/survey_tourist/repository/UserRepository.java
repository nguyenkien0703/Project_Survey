package com.example.survey_tourist.repository;

import com.example.survey_tourist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByUserName (String userName);
    Optional<User> findOneByUserName(String userName);
}
