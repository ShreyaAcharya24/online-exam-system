package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.LoginRequest;
import com.roima.exam.online_exam_system.dto.LoginResponse;
import com.roima.exam.online_exam_system.enums.Role;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final HttpSession httpSession;
    UserService userService;

    @Autowired
    public AuthController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.email();
        String password = loginRequest.password();

        LoginResponse responseDTO;

        if (userService.userLogin(email, password)) {
            User user = userService.findUserByEmail(email);
            System.out.println(user.getRole() + " " + user.getEmail());

            if (user != null & user.getRole() == Role.STUDENT) {
                int studentId = user.getStudent().getStudentId();
                httpSession.setAttribute("studentId", studentId);
                responseDTO = new LoginResponse("success", "Login Successful", "/exam");
                return ResponseEntity.ok(responseDTO);
            } else {
                System.out.println("Admin:  " + user.getRole());
                responseDTO = new LoginResponse("failure", "User is not a student", "/login");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
            }

        }
        responseDTO = new LoginResponse("failure", "Invalid Credentialsssss", "/login");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);

    }
}

