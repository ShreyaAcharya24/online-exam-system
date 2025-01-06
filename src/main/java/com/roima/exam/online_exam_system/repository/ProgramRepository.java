package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.ProgramAns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramAns, Integer> {
}
