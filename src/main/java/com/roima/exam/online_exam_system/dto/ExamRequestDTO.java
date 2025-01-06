package com.roima.exam.online_exam_system.dto;

import com.roima.exam.online_exam_system.model.Institute;
import com.roima.exam.online_exam_system.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record ExamRequestDTO(
        String title,
        String instructions,
        int duration,
        LocalDateTime startTime,
        int totalMarks,
        int passingMarks,
        User createdBy,
        User updatedBy,
        List<Integer> selectQuestionIds
) {
}
