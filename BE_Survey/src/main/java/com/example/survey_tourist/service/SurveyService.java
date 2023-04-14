package com.example.survey_tourist.service;
import com.example.survey_tourist.dto.SurveyDto;
import com.example.survey_tourist.dto.SurveyDtoList;
import com.example.survey_tourist.entity.OptionQuestionSurvey;
import com.example.survey_tourist.entity.QuestionSurvey;
import com.example.survey_tourist.entity.Survey;
import com.example.survey_tourist.repository.SurveyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SurveyRepository surveyRepository;


    @Autowired
    private ModelMapper modelMapper;

    public Page<SurveyDtoList> getAllSurveyResearch(String nameSurvey, LocalDate startDate, LocalDate endDate,
            int pageNumber, int pageSize) {
        Specification<Survey> spec = Specification
                .where((root, query, builder) -> builder.equal(root.get("isDelete"), false));

        if (!nameSurvey.equals("")) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("nameSurvey"), "%" + nameSurvey + "%"));
        }

        if (startDate != null && endDate != null) {
            System.out.println(startDate + " " + endDate);
            spec = spec.and((root, query, builder) -> builder.between(root.get("createAt"), startDate, endDate));
        } else if (startDate != null && endDate == null) {
            System.out.println(startDate + " " + endDate);

            spec = spec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("createAt"), startDate));
        } else if (startDate == null && endDate != null) {
            System.out.println(startDate + " " + endDate);

            spec = spec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("createAt"), endDate));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Survey> pageResult = surveyRepository.findAll(spec, pageable);
        Page<SurveyDtoList> surveyDtoList = pageResult.map(survey -> {
            SurveyDtoList surveyDto = modelMapper.map(survey, SurveyDtoList.class);
            int numberPeopleParticipants = surveyRepository.countParticipantsBySurvey(survey.getId());
            surveyDto.setNumberOfParticipants(numberPeopleParticipants);
            int numberProcess = surveyRepository.countAnswersBySurvey(survey.getId());
            surveyDto.setProgress(numberProcess);
            return surveyDto;
        });
        return surveyDtoList;
    }

    public Long countSurveyInfoDto(String nameSurvey, LocalDate startDate, LocalDate endDate) {
        String query = "SELECT COUNT(*) FROM Survey  WHERE isDelete = false";

        if (nameSurvey != null) {
            query += " AND    nameSurvey  LIKE '%" + nameSurvey + "%'    ";
        }
        if (startDate != null) {
            query += " AND  createAt  >= '" + startDate + "' ";
        }

        if (endDate != null) {
            query += " AND  createAt  <= '" + endDate + "' ";
        }
        return entityManager.createQuery(query, Long.class).getSingleResult();
    }

    public List<SurveyDto> findAll() {
        return surveyRepository.findAll().stream().map(survey -> mapToDTO(survey, new SurveyDto()))
                .collect(Collectors.toList());
    }

    public Survey get(final Integer id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final SurveyDto surveyDto) {
        final Survey survey = new Survey();
        mapToEntity(surveyDto, survey);
        return surveyRepository.save(survey).getId();
    }

    public void update(final Integer id, final SurveyDto surveyDto) {
        final Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        survey.setNameSurvey(surveyDto.getNameSurvey());
        survey.setContentSurvey(surveyDto.getContentSurvey());
        LocalDate Date = LocalDate.now();
        survey.setUpdateAt(Date);
        surveyRepository.save(survey);
    }

    public Survey registerSurvey(SurveyDto surveyDto) throws Exception {
        Survey survey = new Survey();
        survey.setNameSurvey(surveyDto.getNameSurvey());
        survey.setContentSurvey(surveyDto.getContentSurvey());

        LocalDate now = LocalDate.now();
        survey.setCreateAt(now);
        survey.setUpdateAt(now);

        // Set up expiration time of survey is one day
        LocalDate expiration = now.plusDays(1);
        survey.setExpiration(LocalDate.from(expiration));
        surveyRepository.save(survey);

        return survey;
    }

    public void delete(final Integer id) {
        final Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate deleteAt = LocalDate.now();
        survey.setIsDelete(true);
        surveyRepository.save(survey);

    }

    private Survey mapToEntity(final SurveyDto surveyDto, Survey survey) {
        survey = modelMapper.map(surveyDto, Survey.class);
        return survey;
    }

    private SurveyDto mapToDTO(final Survey survey, SurveyDto surveyDto) {
        surveyDto = modelMapper.map(survey, SurveyDto.class);
        return surveyDto;
    }

    public Survey getById(final Integer id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public Page<SurveyDtoList> getAll(String name, int pageNumber, int pageSize) {
        Specification<Survey> spec = Specification.where((root, query, builder) -> builder.equal(root.get("isDelete"), false));
        if (name != null) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("nameSurvey"), "%" + name + "%"));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Survey> pageResult = surveyRepository.findAll(spec, pageable);
        Page<SurveyDtoList> pageDto = pageResult.map(survey -> {
            SurveyDtoList surveyDto = modelMapper.map(survey, SurveyDtoList.class);
            surveyDto.setNameSurvey(survey.getNameSurvey());
            surveyDto.setId(survey.getId());
            surveyDto.setContentSurvey(survey.getContentSurvey());
            return surveyDto;
        });
        return pageDto;
    }
    public Optional<Survey> getSurveyWithQuestionsById(Integer id) {
        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        if (surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            List<QuestionSurvey> questionSurveys = survey.getQuestionSurveys();
            for (QuestionSurvey questionSurvey : questionSurveys) {
                List<OptionQuestionSurvey> optionQuestionSurveys = questionSurvey.getOptionQuestions();
                questionSurvey.setOptionQuestions(optionQuestionSurveys);
            }
            survey.setQuestionSurveys(questionSurveys);
            return Optional.of(survey);
        }
        return Optional.empty();
    }

    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }
}
