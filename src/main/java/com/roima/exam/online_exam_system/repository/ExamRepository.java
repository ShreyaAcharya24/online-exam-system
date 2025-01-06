package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.Exam;
import com.roima.exam.online_exam_system.model.ExamQuestion;
import com.roima.exam.online_exam_system.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {
}
