package com.roima.exam.online_exam_system.model;

import com.roima.exam.online_exam_system.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    public int getUser_id() {
        return user_id;
    }
}
