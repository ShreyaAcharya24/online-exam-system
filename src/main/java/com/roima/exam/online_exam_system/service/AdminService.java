package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.AdminRequestDTO;
import com.roima.exam.online_exam_system.dto.AdminResponseDTO;
import com.roima.exam.online_exam_system.enums.Role;
import com.roima.exam.online_exam_system.model.Admin;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    private final BCryptPasswordEncoder pwdEncoder;
    private final UserService userService;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(BCryptPasswordEncoder pwdEncoder,UserService userService, AdminRepository adminRepository) {
        this.userService = userService;
        this.adminRepository = adminRepository;
        this.pwdEncoder = pwdEncoder;
    }


    public AdminResponseDTO addAdmin(AdminRequestDTO adminRequestDTO) {

//      email , pwd , role in User
        User user = new User();
        user.setEmail(adminRequestDTO.email());
        String hashedPwd = pwdEncoder.encode(adminRequestDTO.password());
        user.setPassword(hashedPwd);
        user.setRole(Role.ADMIN);
        User createdUser = userService.addUser(user);
        System.out.println(
                "C+++++ " + createdUser.getUser_id() + " " + createdUser.getEmail()
        );

    //    fname , lname, contact in Admin
        Admin admin = new Admin();
        admin.setFirstname(adminRequestDTO.first_name());
        admin.setLastname(adminRequestDTO.last_name());
        admin.setContact(adminRequestDTO.contact());
        // Link admin to user

        admin.setUser(createdUser);

        Admin createdAdmin = adminRepository.save(admin);
        System.out.println( " ***** " + createdAdmin.getAdmin_id() + " " + createdAdmin.getFirstname());

//        Prepare Response DTO
        return new AdminResponseDTO(
                createdAdmin.getFirstname(),
                createdAdmin.getLastname(),
                createdAdmin.getContact(),
                createdAdmin.getUser().getEmail()
        );

    }

    public Admin findById(int id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not Found"));
    }

}
