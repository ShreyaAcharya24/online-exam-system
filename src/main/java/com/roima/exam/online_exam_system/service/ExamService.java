package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.ExamRequestDTO;
import com.roima.exam.online_exam_system.dto.OptionDTO;
import com.roima.exam.online_exam_system.dto.QuestionDTO;
import com.roima.exam.online_exam_system.enums.Availability;
import com.roima.exam.online_exam_system.model.*;
import com.roima.exam.online_exam_system.repository.ExamQuestionRepository;
import com.roima.exam.online_exam_system.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    private final ExamQuestionRepository examQuestionRepository;
    private final OptionService optionService;
    ExamRepository examRepository;
    UserService userService;

    @Autowired
    public ExamService(ExamRepository examRepository, UserService userService, ExamQuestionRepository examQuestionRepository, OptionService optionService) {
        this.examRepository = examRepository;
        this.userService = userService;
        this.examQuestionRepository = examQuestionRepository;
        this.optionService = optionService;
    }


    public Exam createExam(ExamRequestDTO examRequestDTO) {

        Exam newExam = new Exam();
        newExam.setTitle(examRequestDTO.title());
        newExam.setInstructions(examRequestDTO.instructions());
        newExam.setDuration(examRequestDTO.duration());
        newExam.setStartTime(examRequestDTO.startTime());
        newExam.setTotalMarks(examRequestDTO.totalMarks());
        newExam.setPassingMarks(examRequestDTO.passingMarks());
        // Fetch and set createdBy and updatedBy
        User createdBy = userService.findById(examRequestDTO.createdBy().getUser_id());
        System.out.println(" ***** " + examRequestDTO.createdBy() + " ****** created by " + createdBy);
        newExam.setCreatedBy(createdBy);

        User updatedBy = userService.findById(examRequestDTO.updatedBy().getUser_id());
        newExam.setUpdatedBy(updatedBy);

        // Fetch and set Institute
//        System.out.println(examRequestDTO.institute());
//        Institute institute = instituteService.findById(examRequestDTO.institute().getInstituteId());
//        System.out.println(institute.getInstituteId() + institute.getInstituteName());
//        newExam.setInstitute(institute);

        examRepository.save(newExam);
        return newExam;
    }

    public Exam findById(int id) {
        return examRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not Found"));

    }

    public boolean isExamAvailable(int eid) {
        Exam exam = examRepository.findById(eid).orElseThrow(() -> new RuntimeException("Exam not found"));
        if ((exam.getExamStatus() == Availability.Available) & (exam.getStartTime().isAfter(LocalDateTime.now()))) {
            return true;
        }
        return false;
    }

//    ************ Get All Questions For Exam **************

    public List<QuestionDTO> getExamQuestions(int examId) {
//        Get all the questions for a specific exam
        List<ExamQuestion> questions = examQuestionRepository.findByExamExamId(examId);

//      List of questions  Options to display to user
        List<QuestionDTO> questionsList = new ArrayList<>();

        for (ExamQuestion examquestion : questions) {

            QuestionBank question = examquestion.getQuestion();

            List<OptionDTO> optionsList = new ArrayList<>();
            if (question.getType().getTypeName().equals("MCQ")) {
                for (Option option : optionService.getOptionsByQueId(question.getQuestionId())) {
                    OptionDTO optionDTO = new OptionDTO(
                            option.getOptionId(),
                            option.getOptionText(),
                            option.isIs_correct()
                    );
                    optionsList.add(optionDTO);
                }
                QuestionDTO questionDTO = new QuestionDTO(
                        question.getQuestionId(),
                        question.getQuestion_text(),
                        optionsList
                );
                questionsList.add(questionDTO);
            }

        }
        return questionsList;
    }
}
