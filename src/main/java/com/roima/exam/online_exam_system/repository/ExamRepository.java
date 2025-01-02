package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
}
