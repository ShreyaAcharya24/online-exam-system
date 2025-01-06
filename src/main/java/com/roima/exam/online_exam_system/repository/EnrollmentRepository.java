package com.roima.exam.online_exam_system.repository;

import com.roima.exam.online_exam_system.model.Enrollment;
import com.roima.exam.online_exam_system.model.Exam;
import com.roima.exam.online_exam_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
            Optional<Enrollment> findByExamAndStudent(Exam exam, Student student);
            Optional<Enrollment> findByStudent_StudentId(int sid);
}
