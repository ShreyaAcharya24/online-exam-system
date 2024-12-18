package com.roima.exam.online_exam_system.controller;
import com.roima.exam.online_exam_system.dto.StudentRequestDTO;
import com.roima.exam.online_exam_system.dto.StudentResponseDTO;
import com.roima.exam.online_exam_system.model.Student;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.service.StudentService;
import com.roima.exam.online_exam_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO studentRequestDTO){

        User user = new User();
        user.setFirst_name(studentRequestDTO.getFirst_name());
        user.setLast_name(studentRequestDTO.getLast_name());
        user.setEmail(studentRequestDTO.getEmail());

        String hashedPwd = pwdEncoder.encode(studentRequestDTO.getPassword());
        user.setPassword(hashedPwd);
        user.setRole("Student");

//      Save common data in user table
        User createdUser = userService.addUser(user);

        Student student = new Student();
        student.setUser_id(createdUser.getUser_id());
        student.setContact(studentRequestDTO.getContact());
        student.setInstitute_id(studentRequestDTO.getInstitute_id());

        Student createdStudent = studentService.addStudent(student);

        System.out.println("\n***** Student Added ***");

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudent_id(createdStudent.getStudent_id());
        studentResponseDTO.setInstitute_id(createdStudent.getInstitute_id());
        studentResponseDTO.setUser_id(createdUser.getUser_id());
        studentResponseDTO.setFirst_name(createdUser.getFirst_name());
        studentResponseDTO.setLast_name(createdUser.getLast_name());
        studentResponseDTO.setEmail(createdUser.getEmail());

        return new ResponseEntity<>(studentResponseDTO, HttpStatus.CREATED);
    }


}
