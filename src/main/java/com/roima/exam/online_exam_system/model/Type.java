package com.roima.exam.online_exam_system.model;


import jakarta.persistence.*;


@Entity
@Table(name = "questionType")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    private String typeName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int type_id) {
        this.typeId = type_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String type_name) {
        this.typeName = type_name;
    }




}
