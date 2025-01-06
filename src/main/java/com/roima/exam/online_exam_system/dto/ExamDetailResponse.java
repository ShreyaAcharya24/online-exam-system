package com.roima.exam.online_exam_system.dto;

import java.util.Map;

public record ExamDetailResponse(
        String message,
        Map<String, Object> data
) {
}
