package com.roima.exam.online_exam_system.model;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_answer")
public class StudentAnswer {

    @EmbeddedId
    private StudentAnswerId answerId;

    @Column(columnDefinition = "TEXT")
    private String answerText;

    @Column(nullable = false)
    private double marksEarned;

    public StudentAnswerId getAnswerId() {
        return answerId;
    }

    public void setAnswerId(StudentAnswerId answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public double getMarksEarned() {
        return marksEarned;
    }

    public void setMarksEarned(double marksEarned) {
        this.marksEarned = marksEarned;
    }
}
