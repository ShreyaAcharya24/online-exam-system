package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

}
