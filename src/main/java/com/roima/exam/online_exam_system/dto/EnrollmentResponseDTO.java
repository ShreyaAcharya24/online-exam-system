package com.roima.exam.online_exam_system.dto;

import com.roima.exam.online_exam_system.model.Enrollment;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public record EnrollmentResponseDTO(HttpStatus status,
                                    List<Map<String, Object>> successfulEnrollments,
                                    List<String> failedEnrollments
) {
}
