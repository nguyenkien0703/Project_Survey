package com.example.survey_tourist;

import com.example.survey_tourist.entity.*;
import com.example.survey_tourist.repository.DepartmentRepository;
import com.example.survey_tourist.repository.RoleRepository;
import com.example.survey_tourist.repository.SurveyRepository;
import com.example.survey_tourist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


@SpringBootApplication
public class SurveyTouristApplication   {

	public static void main(String[] args) {
		SpringApplication.run(SurveyTouristApplication.class, args);
	}
}
