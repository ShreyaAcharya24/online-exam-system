package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.ExamQuestion;
import com.roima.exam.online_exam_system.model.ExamQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, ExamQuestionId> {
}
