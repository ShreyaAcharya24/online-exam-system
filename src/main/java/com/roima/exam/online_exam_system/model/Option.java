package com.roima.exam.online_exam_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int optionId;

    @Column(nullable = false)
    private String optionText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionBank question;

    @Column(nullable = false)
    private boolean is_correct;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public QuestionBank getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBank question) {
        this.question = question;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
}
