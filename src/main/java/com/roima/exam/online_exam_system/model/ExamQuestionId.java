package com.roima.exam.online_exam_system.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ExamQuestionId implements Serializable {
    private int examId;
    private int questionId;

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
