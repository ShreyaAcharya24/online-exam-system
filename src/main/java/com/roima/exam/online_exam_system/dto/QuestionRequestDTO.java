package com.roima.exam.online_exam_system.dto;

import com.roima.exam.online_exam_system.model.Category;
import com.roima.exam.online_exam_system.model.Difficulty;
import com.roima.exam.online_exam_system.model.Type;
import com.roima.exam.online_exam_system.model.User;

import java.util.List;

public record QuestionRequestDTO(
        String questionText,
        Double marks,
        Type type,
        Category category,
        Difficulty difficulty,
        User createdBy,
        User updatedBy,
        List<OptionRequestDTO> options,
        String progAns
) {
}
