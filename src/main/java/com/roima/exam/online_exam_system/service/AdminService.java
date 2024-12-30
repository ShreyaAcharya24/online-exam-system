package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.repository.AdminRepository;
import com.roima.exam.online_exam_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private StudentRepository studentRepository;
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
    }

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
