package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty,Integer> {
}
