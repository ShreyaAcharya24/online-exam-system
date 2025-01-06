package com.roima.exam.online_exam_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "difficulty")
public class Difficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int levelId;
    private String levelName;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int level_id) {
        this.levelId = level_id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String level_name) {
        this.levelName = level_name;
    }
}
