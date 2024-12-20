package com.roima.exam.online_exam_system.model;

import jakarta.persistence.*;

@Entity
@Table(name="institute")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int institute_id;

    private String institute_name;
    private String contact;
    private String address;

    public int getInstitute_id() {
        return institute_id;
    }

    public void setInstitute_id(int institute_id) {
        this.institute_id = institute_id;
    }

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
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
