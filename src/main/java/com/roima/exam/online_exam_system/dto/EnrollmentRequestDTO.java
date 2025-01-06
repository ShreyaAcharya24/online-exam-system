package com.roima.exam.online_exam_system.dto;

import java.util.List;

public record EnrollmentRequestDTO(
        int examId,
        int adminId,
        List<Integer> students
) {
}
