package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.*;
import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.model.Exam;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final AdminService adminService;
    private final HttpSession httpSession;
    private final EnrollmentService enrollmentService;
    private final QuestionBankService questionBankService;
    ExamService examService;
    ExamQuestionService examQuestionService;
    UserService userService;

    @Autowired
    public ExamController(UserService userService, ExamService examService, ExamQuestionService examQuestionService, AdminService adminService, HttpSession httpSession, EnrollmentService enrollmentService, QuestionBankService questionBankService) {
        this.examService = examService;
        this.userService = userService;
        this.examQuestionService = examQuestionService;
        this.adminService = adminService;
        this.httpSession = httpSession;
        this.enrollmentService = enrollmentService;
        this.questionBankService = questionBankService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody ExamRequestDTO examRequestDTO) {

        try {
//        Call createExam
            Exam exam = examService.createExam(examRequestDTO);

//   ****** Imp. learn properly (USes left join admin)******
            User user = userService.findById(examRequestDTO.createdBy().getUser_id());
            Admin admin = user.getAdmin();
            String adminName = admin.getFirstname() + " " + admin.getLastname();
            System.out.println(" **** " + adminName);

            //Add questions in examque table
            Boolean isQuestionAdded = examQuestionService.addQuestionsToExam(exam.getExamId(), examRequestDTO.selectQuestionIds());

            ExamResponseDTO examResponseDTO = new ExamResponseDTO(
                    exam.getTitle(),
                    exam.getInstructions(),
                    exam.getDuration(),
                    exam.getStartTime(),
                    exam.getTotalMarks(),
                    exam.getPassingMarks(),
                    adminName
            );

            return new ResponseEntity<>(examResponseDTO,HttpStatus.CREATED);
        } catch (RuntimeException e) {

            String error = "Failed to create the exam " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(error));

        }
    }


//    ****** Get Exam Details *************
    @GetMapping("/get-questions/{examId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsinExam(@PathVariable int examId){
        List<QuestionDTO> displayQuestions = examService.getExamQuestions(examId);
        return ResponseEntity.ok(displayQuestions);

    }

}
