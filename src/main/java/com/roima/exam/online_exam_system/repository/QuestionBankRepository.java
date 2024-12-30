package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBankRepository extends JpaRepository<QuestionBank,Integer> {
}
