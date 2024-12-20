package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public boolean userLogin(String email,String pwd){

        User user = userRepository.findByEmail(email);

        if(user != null ){
            return pwdEncoder.matches(pwd,user.getPassword());
        }
        return false;
    }

}
