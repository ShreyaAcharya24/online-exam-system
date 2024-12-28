package com.roima.exam.online_exam_system.enums;

public enum Role {

    ADMIN,STUDENT,SUPER_ADMIN;

        @Override
        public String toString() {
            return switch (this) {
                case ADMIN -> "Admin";
                case STUDENT -> "Student";
                case SUPER_ADMIN -> "SuperAdmin";
            };
        }
    }

