package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.QuestionTypeDto;
import com.example.survey_tourist.entity.QuestionType;
import com.example.survey_tourist.entity.Role;
import com.example.survey_tourist.repository.QuestionTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionTypeService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;


    public List<QuestionTypeDto> findAll(){
        return questionTypeRepository.findAll().stream().map(questionType -> mapToDTO(questionType, new QuestionTypeDto())).collect(Collectors.toList());
    }
    public List<QuestionType> findAllQT(){
        return questionTypeRepository.findAll();
    }
    public QuestionType get(final Integer id) {
        return questionTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public Integer create(final QuestionTypeDto questionTypeDto) {
        final QuestionType questionType = new QuestionType();
        mapToEntity(questionTypeDto, questionType);
        return questionTypeRepository.save(questionType).getId();

    }
    public QuestionType registerQuestionType (QuestionTypeDto questionTypeDto) throws Exception {
        QuestionType questionType = new QuestionType();
        questionType.setNameType(questionTypeDto.getNameType());

        LocalDate now = LocalDate.now();

        questionType.setCreateAt(now);
        questionType.setUpdateAt(now);
        questionTypeRepository.save(questionType);
        return questionType;
    }

    public void update (final Integer id , final QuestionTypeDto questionTypeDto){
        final QuestionType questionType = questionTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        questionType.setNameType(questionType.getNameType());
        questionTypeRepository.save(questionType);
    }

    public void delete(final Integer id ){

        final QuestionType questionType = questionTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate  deleteAt = LocalDate.now();
        questionType.setIsDelete(true);
        questionTypeRepository.save(questionType);
    }



    private QuestionType mapToEntity(final QuestionTypeDto questionTypeDto, QuestionType questionType ){
        questionType = modelMapper.map(questionTypeDto,QuestionType.class);
        return  questionType;
    }
    private QuestionTypeDto mapToDTO(final QuestionType questionType, QuestionTypeDto questionTypeDto){
        questionTypeDto = modelMapper.map(questionType,QuestionTypeDto.class);
        return questionTypeDto;
    }

}
