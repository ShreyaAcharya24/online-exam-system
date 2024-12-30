package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.AdminRequestDTO;
import com.roima.exam.online_exam_system.dto.AdminResponseDTO;
import com.roima.exam.online_exam_system.enums.Role;
import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.service.AdminService;
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
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @PostMapping("/add")
    public ResponseEntity<AdminResponseDTO> addAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {

        User user = new User();
        user.setEmail(adminRequestDTO.getEmail());

        String hashedPwd = pwdEncoder.encode(adminRequestDTO.getPassword());
        user.setPassword(hashedPwd);
        user.setRole(Role.ADMIN);

        User createdUser = userService.addUser(user);

        Admin admin = new Admin();
        admin.setContact(adminRequestDTO.getContact());

        Admin createdAdmin = adminService.addAdmin(admin);
        AdminResponseDTO adminResponseDTO = new AdminResponseDTO();
        adminResponseDTO.setAdmin_id(createdAdmin.getAdmin_id());
        adminResponseDTO.setUser_id(createdUser.getUser_id());
        adminResponseDTO.setEmail(createdUser.getEmail());
        return new ResponseEntity<>(adminResponseDTO, HttpStatus.CREATED);
    }

}
