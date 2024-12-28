package com.roima.exam.online_exam_system.model;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int category_id) {
        this.categoryId = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category_name) {
        this.categoryName = category_name;
    }
}
