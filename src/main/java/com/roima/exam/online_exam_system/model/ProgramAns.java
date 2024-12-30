package com.roima.exam.online_exam_system.model;


import jakarta.persistence.*;

@Entity
@Table(name = "program_ans")
public class ProgramAns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ansId;

    @Column(nullable = false)
    private String ansText;

    @OneToOne
    @JoinColumn(name = "question_id")
    private QuestionBank question;

    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
    }

    public String getAnsText() {
        return ansText;
    }

    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }

    public QuestionBank getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBank question) {
        this.question = question;
    }
}
