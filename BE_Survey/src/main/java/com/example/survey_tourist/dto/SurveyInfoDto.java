package com.example.survey_tourist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
// survey'info display
public class SurveyInfoDto implements Serializable {
    private Integer id;

    private String nameSurvey;
    private Integer numberOfParticipants;
    private Integer progress;
    private String expiration;



}
