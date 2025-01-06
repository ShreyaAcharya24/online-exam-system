package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {


}
