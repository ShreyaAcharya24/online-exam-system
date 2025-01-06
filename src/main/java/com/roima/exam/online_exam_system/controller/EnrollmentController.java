package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.EnrollmentRequestDTO;
import com.roima.exam.online_exam_system.dto.EnrollmentResponseDTO;
import com.roima.exam.online_exam_system.dto.ExamDetailResponse;
import com.roima.exam.online_exam_system.model.Enrollment;
import com.roima.exam.online_exam_system.service.EnrollmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final HttpSession httpSession;
    private EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, HttpSession httpSession) {
        this.enrollmentService = enrollmentService;
        this.httpSession = httpSession;
    }

    @PostMapping("/add")
    public ResponseEntity<EnrollmentResponseDTO> enrollStudentsToExam(@RequestBody EnrollmentRequestDTO enrollmentRequestDTO) {
        EnrollmentResponseDTO responseDTO = enrollmentService.enrollStudent(enrollmentRequestDTO);
        return new ResponseEntity<>(responseDTO, responseDTO.status());
    }

    @GetMapping("/get/{id}")
    public Enrollment getById(@PathVariable int id) {
        return enrollmentService.getElementbyId(id);
    }

    @GetMapping("/check-eligibility")
    public ResponseEntity<ExamDetailResponse> checkStudentEligibility(){

        Integer studentId = (Integer) httpSession.getAttribute("studentId");

        if(studentId == null ){
            return  ResponseEntity.status(401).body(new ExamDetailResponse("Unauthorised: No Student Id in session",null));
        }
        ExamDetailResponse response = enrollmentService.checkEligibility(studentId);
        return ResponseEntity.ok(response);
    }

}
