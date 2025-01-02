package com.roima.exam.online_exam_system.dto;

import com.roima.exam.online_exam_system.model.User;

import java.time.LocalDateTime;

public record ExamResponseDTO(
        String title,
        String instructions,
        int duration,
        LocalDateTime startTime,
        int totalMarks,
        int passingMarks,
        String createdBy
) {
}
