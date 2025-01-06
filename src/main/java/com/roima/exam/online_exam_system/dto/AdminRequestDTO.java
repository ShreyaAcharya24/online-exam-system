package com.roima.exam.online_exam_system.dto;

public record AdminRequestDTO(
         String first_name,
         String last_name,
         String email,
         String password,
         String role,
         String contact) {
}
