package com.roima.exam.online_exam_system.dto;

import java.util.List;

public record QuestionDTO(
        int questionId,
        String questionText,
        List<OptionDTO> options
) {
}
