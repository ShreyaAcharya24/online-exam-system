package com.roima.exam.online_exam_system.dto;

import com.roima.exam.online_exam_system.enums.Role;

import java.time.LocalDateTime;

public record QuestionResponseDTO(
        String questiontext,
        int marks,
        String quecategory,
        String quetype,
        String quedifficulty,
        String email, // try to send Name instead of email
        Role role,
        LocalDateTime createdAt) {
}
