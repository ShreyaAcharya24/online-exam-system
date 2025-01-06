package com.roima.exam.online_exam_system.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentAnswerId implements Serializable {

    private  int studentId;
    private int questionId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAnswerId that = (StudentAnswerId) o;
        return studentId == that.studentId && questionId == that.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, questionId);
    }
}
