package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {


    private UserRepository userRepository;
    private BCryptPasswordEncoder pwdEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder pwdEncoder) {
        this.userRepository = userRepository;
        this.pwdEncoder = pwdEncoder;
    }

    public User addUser(User user) {
//        hash the password and then save
        user.setPassword(pwdEncoder.encode(user.getPassword()));
//        System.out.println(user.getRole() + " --" + user.getEmail() + "-- " + user.getPassword());
        return userRepository.save(user);
    }

    public boolean userLogin(String email, String pwd) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return pwdEncoder.matches(pwd, user.getPassword());
        }
        return false;
    }

    //    Update User
    public User updateUser(User user) {

        User currentUser = userRepository.findById(user.getUser_id()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        currentUser.setEmail(user.getEmail());
//        currentUser.setRole(user.getRole());
        return userRepository.save(currentUser);
    }

//    Find By Id

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found"));

    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
