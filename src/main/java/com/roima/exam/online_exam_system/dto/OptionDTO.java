package com.roima.exam.online_exam_system.dto;

public record OptionDTO(
        int optionId,
        String optionText,
        boolean isCorrect
) {
}
