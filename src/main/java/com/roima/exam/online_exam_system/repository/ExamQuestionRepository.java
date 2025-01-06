package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.ExamQuestion;
import com.roima.exam.online_exam_system.model.ExamQuestionId;
import com.roima.exam.online_exam_system.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, ExamQuestionId> {
        List<ExamQuestion> findByExamExamId(int examId);
}
