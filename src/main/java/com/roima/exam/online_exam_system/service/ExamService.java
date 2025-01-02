package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.ExamRequestDTO;
import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.model.Exam;
import com.roima.exam.online_exam_system.model.Institute;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.repository.ExamQuestionRepository;
import com.roima.exam.online_exam_system.repository.ExamRepository;
import com.roima.exam.online_exam_system.repository.QuestionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExamService {

    private final InstituteService instituteService;
    private final UserService userService;
    ExamRepository examRepository;
    QuestionBankRepository questionBankRepository;

    @Autowired
    public ExamService(ExamRepository examRepository, QuestionBankRepository questionBankRepository, InstituteService instituteService, UserService userService) {
        this.examRepository = examRepository;
        this.questionBankRepository = questionBankRepository;
        this.instituteService = instituteService;
        this.userService = userService;
    }

    public Exam createExam(ExamRequestDTO examRequestDTO){

        Exam newExam = new Exam();
        newExam.setTitle(examRequestDTO.title());
        newExam.setInstructions(examRequestDTO.instructions());
        newExam.setDuration(examRequestDTO.duration());
        newExam.setStartTime(examRequestDTO.startTime());
        newExam.setTotalMarks(examRequestDTO.totalMarks());
        newExam.setPassingMarks(examRequestDTO.passingMarks());
        // Fetch and set createdBy and updatedBy
        User createdBy = userService.findById(examRequestDTO.createdBy().getUser_id());
        System.out.println( " ***** " +  examRequestDTO.createdBy() + " ****** created by " + createdBy);
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


}
