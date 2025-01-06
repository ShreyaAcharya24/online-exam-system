package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.AdminRequestDTO;
import com.roima.exam.online_exam_system.dto.AdminResponseDTO;
import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public ResponseEntity<AdminResponseDTO> addAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {

        AdminResponseDTO adminResponseDTO = adminService.addAdmin(adminRequestDTO);
        return new ResponseEntity<>(adminResponseDTO, HttpStatus.CREATED);
    }

}
