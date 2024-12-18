package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.repository.AdminRepository;
import com.roima.exam.online_exam_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

}
