package com.roima.exam.online_exam_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_question")
public class ExamQuestion {

    @EmbeddedId
    private ExamQuestionId id;

    @ManyToOne
    @MapsId("examId")  // use the examId from composite key
    @JoinColumn(name = "exam_id", referencedColumnName = "examId")
    // exam_id is the column in the exam_question table & examId is pk in exam entity
    private Exam exam;

    @ManyToOne
    @MapsId("questionId") // use the questionId from composite key
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    // question_id is column name in exam_question table, questionId pk in QuestionBank
    private QuestionBank question;

    public ExamQuestionId getId() {
        return id;
    }

    public void setId(ExamQuestionId id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public QuestionBank getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBank question) {
        this.question = question;
    }
}
