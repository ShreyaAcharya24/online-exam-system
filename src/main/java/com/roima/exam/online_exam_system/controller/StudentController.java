package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.StudentRequestDTO;
import com.roima.exam.online_exam_system.dto.StudentResponseDTO;
import com.roima.exam.online_exam_system.service.StudentService;
import com.roima.exam.online_exam_system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    private StudentService studentService;
    private UserService userService;
    private BCryptPasswordEncoder pwdEncoder;

    public StudentController(StudentService studentService, UserService userService, BCryptPasswordEncoder pwdEncoder) {
        this.studentService = studentService;
        this.userService = userService;
        this.pwdEncoder = pwdEncoder;
    }

    //    Add Student
    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO studentResponseDTO = studentService.addStudent(studentRequestDTO);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.CREATED);
    }

    //    Get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    //    Delete Student
    @DeleteMapping("/delete/{sid}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "sid") int studentid) {
        boolean isDeleted = studentService.deleteStudent(studentid);
        if (isDeleted) {
            return ResponseEntity.ok("Student Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    //     *** Update Student
    @PutMapping("/update/{sid}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable(name = "sid") int id) {

        StudentResponseDTO updatedResponseDTO = studentService.updateStudent(studentRequestDTO, id);
        return new ResponseEntity<>(updatedResponseDTO, HttpStatus.OK);

    }


}
