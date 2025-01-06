package com.roima.exam.online_exam_system.dto;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class ErrorResponseDTO {

    private String message;

    private LocalDateTime timestamp;

    public ErrorResponseDTO(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage(){
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
