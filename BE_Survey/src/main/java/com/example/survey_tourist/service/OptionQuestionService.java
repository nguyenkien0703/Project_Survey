package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.DepartmentDto;
import com.example.survey_tourist.dto.OptionQuestionDto;
import com.example.survey_tourist.dto.SurveyDto;
import com.example.survey_tourist.dto.UserDto;
import com.example.survey_tourist.entity.Department;
import com.example.survey_tourist.entity.OptionQuestionSurvey;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.repository.OptionQuestionSurveyRepository;
import com.example.survey_tourist.repository.QuestionSurveyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OptionQuestionService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private static OptionQuestionSurveyRepository optionQuestionSurveyRepository;
    @Autowired
    QuestionSurveyRepository questionSurveyRepository;

    public List<OptionQuestionDto> findAll(){
        return optionQuestionSurveyRepository.findAll().stream().map(optionQuestionSurvey -> mapToDTO(optionQuestionSurvey, new OptionQuestionDto())).collect(Collectors.toList());
    }
    public OptionQuestionSurvey get(final Integer id) {
        return optionQuestionSurveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public Integer create(final OptionQuestionDto optionQuestionDto) {
        final OptionQuestionSurvey optionQuestionSurvey = new OptionQuestionSurvey();
        mapToEntity(optionQuestionDto, optionQuestionSurvey);
        return optionQuestionSurveyRepository.save(optionQuestionSurvey).getId();
    }

    public void update(final Integer id, final OptionQuestionDto optionQuestionDto){
        final OptionQuestionSurvey optionQuestionSurvey = optionQuestionSurveyRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        optionQuestionSurvey.setNameOption(optionQuestionDto.getNameOption());
        optionQuestionSurveyRepository.save(optionQuestionSurvey);
    }

    public OptionQuestionSurvey registerOptionQuestionSurvey(OptionQuestionDto optionQuestionDto) throws Exception {
        OptionQuestionSurvey optionQuestionSurvey = new OptionQuestionSurvey();
        optionQuestionSurvey.setNameOption(optionQuestionDto.getNameOption());
        optionQuestionSurvey.setQuestionSurvey(questionSurveyRepository.findById(optionQuestionDto.getQuestionSurveyId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        optionQuestionSurveyRepository.save(optionQuestionSurvey);
        return optionQuestionSurvey;
    }



    public void  delete(final Integer id){
        final OptionQuestionSurvey optionQuestionSurvey= optionQuestionSurveyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        optionQuestionSurvey.setIsDelete(true);
        optionQuestionSurveyRepository.save(optionQuestionSurvey);
    }

    private OptionQuestionSurvey mapToEntity(final OptionQuestionDto optionQuestionDto, OptionQuestionSurvey optionQuestionSurvey ){
        optionQuestionSurvey = modelMapper.map(optionQuestionDto,OptionQuestionSurvey.class);
        return optionQuestionSurvey ;
    }
    private OptionQuestionDto mapToDTO(final OptionQuestionSurvey optionQuestionSurvey, OptionQuestionDto optionQuestionDto){
        optionQuestionDto = modelMapper.map(optionQuestionSurvey,OptionQuestionDto.class);
        return optionQuestionDto;
    }
    public OptionQuestionSurvey save(OptionQuestionSurvey optionQuestionSurvey) {
       return optionQuestionSurveyRepository.save(optionQuestionSurvey);
    }
}
