package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
