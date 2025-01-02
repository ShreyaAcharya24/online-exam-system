package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.ErrorResponseDTO;
import com.roima.exam.online_exam_system.dto.ExamRequestDTO;
import com.roima.exam.online_exam_system.dto.ExamResponseDTO;
import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.model.Exam;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.service.AdminService;
import com.roima.exam.online_exam_system.service.ExamQuestionService;
import com.roima.exam.online_exam_system.service.ExamService;
import com.roima.exam.online_exam_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final AdminService adminService;
    ExamService examService;
    ExamQuestionService examQuestionService;
    UserService userService;

    @Autowired
    public ExamController(UserService userService,ExamService examService, ExamQuestionService examQuestionService, AdminService adminService) {
        this.examService = examService;
        this.userService = userService;
        this.examQuestionService = examQuestionService;
        this.adminService = adminService;
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
}
