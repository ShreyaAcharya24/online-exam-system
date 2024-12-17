package com.roima.exam.online_exam_system.dto;

import jakarta.persistence.*;

public class InstituteResponseDTO {

    private int instiute_id;
    private String institute_name;

    public int getInstiute_id() {
        return instiute_id;
    }

    public void setInstiute_id(int instiute_id) {
        this.instiute_id = instiute_id;
    }

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }
}