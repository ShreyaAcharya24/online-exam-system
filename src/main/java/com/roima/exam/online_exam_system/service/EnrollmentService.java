package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.EnrollmentRequestDTO;
import com.roima.exam.online_exam_system.dto.EnrollmentResponseDTO;
import com.roima.exam.online_exam_system.dto.ExamDetailResponse;
import com.roima.exam.online_exam_system.dto.ExamResponseDTO;
import com.roima.exam.online_exam_system.enums.Result;
import com.roima.exam.online_exam_system.enums.Status;
import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.model.Enrollment;
import com.roima.exam.online_exam_system.model.Exam;
import com.roima.exam.online_exam_system.model.Student;
import com.roima.exam.online_exam_system.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class EnrollmentService {

    private AdminService adminService;
    private ExamService examService;
    private StudentService studentService;
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(StudentService studentService, EnrollmentRepository enrollmentRepository, ExamService examService, AdminService adminService) {
        this.studentService = studentService;
        this.enrollmentRepository = enrollmentRepository;
        this.examService = examService;
        this.adminService = adminService;
    }

    public EnrollmentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO enrollmentRequestDTO) {
        List<Map<String, Object>> successfulEnrolments = new ArrayList<>();
        List<String> failedEnrollments = new ArrayList<>();

        Exam exam = examService.findById(enrollmentRequestDTO.examId());
        Admin admin = adminService.findById(enrollmentRequestDTO.adminId());
        boolean isSuccess = true;

        for (int sid : enrollmentRequestDTO.students()) {

            try {
                Student student = studentService.getByID(sid);

//              Check if student is already enrolled in exam
                Optional<Enrollment> studentExists = enrollmentRepository.findByExamAndStudent(exam, student);

                if (studentExists.isPresent()) {
                    failedEnrollments.add("Student ID: " + sid + " is already enrolled in Exam ID: " + enrollmentRequestDTO.examId());
                    continue;
                }
                Enrollment enrollment = new Enrollment();
                enrollment.setExam(exam);
                enrollment.setStudent(student);
                enrollment.setResult(Result.NA);
                enrollment.setStatus(Status.NotAttempted);
                enrollment.setCreatedBy(admin);

                enrollmentRepository.save(enrollment);

//              ****** Prepare Response *********
                Map<String, Object> enrollmentData = new HashMap<>();
                enrollmentData.put("Exam Title: ", exam.getTitle());
                enrollmentData.put("Creation Time: ", exam.getCreatedAt());
                enrollmentData.put("Student Name: ", student.getFirstname() + " " + student.getLastname());
                enrollmentData.put("Student's Institute Name: ", student.getInstitute().getInstituteName());
                successfulEnrolments.add(enrollmentData);
            } catch (Exception e) {
                failedEnrollments.add("Failed to enroll students some exception occured: " + e.getMessage());
                isSuccess = false;
            }
        }

        HttpStatus status = HttpStatus.OK;
        if (!failedEnrollments.isEmpty()) {
            if (successfulEnrolments.isEmpty()) {
                status = HttpStatus.BAD_REQUEST; //No record added, everything failed
            } else {
                status = HttpStatus.PARTIAL_CONTENT; //Some succeeded, some failed
            }
        }
        return new EnrollmentResponseDTO(status, successfulEnrolments, failedEnrollments);
    }

    public Enrollment getElementbyId(int id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not Found"));
    }

    public ExamDetailResponse checkEligibility(int sid) {
//   Check if student is enrolled in the exam
        Enrollment enrollment = enrollmentRepository.findByStudent_StudentId(sid).orElseThrow(() -> new RuntimeException("Student not enrolled in any exam"));

//     If the exam status is Not attempted then anly allow
        if (enrollment.getStatus() == Status.Attempted || enrollment.getStatus() == Status.Completed) {
            return new ExamDetailResponse("Your are not eligible to appear for this exam", null);
        }
//     check if exam is available
        else if (examService.isExamAvailable(enrollment.getExam().getExamId())) {
            Exam exam = enrollment.getExam();
            Map<String, Object> data = new HashMap<>();
            data.put("examId",exam.getExamId());
            data.put("title", exam.getTitle());
            data.put("instructions", exam.getInstructions());
            data.put("totalmarks", exam.getTotalMarks());
            data.put("duration", exam.getDuration());
            return new ExamDetailResponse("Please click on start button to start the exam", data);
        }
        return new ExamDetailResponse("Exam Not available at the moment", null);
    }
}
