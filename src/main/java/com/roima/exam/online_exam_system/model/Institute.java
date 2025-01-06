package com.roima.exam.online_exam_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "institute")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int institute_id;

    private String institute_name;
    private String contact;
    private String address;

    public int getInstituteId() {
        return institute_id;
    }

    public void setInstituteId(int institute_id) {
        this.institute_id = institute_id;
    }

    public String getInstituteName() {
        return institute_name;
    }

    public void setInstituteName(String institute_name) {
        this.institute_name = institute_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
