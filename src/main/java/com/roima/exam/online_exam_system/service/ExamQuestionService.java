package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.Exam;
import com.roima.exam.online_exam_system.model.ExamQuestion;
import com.roima.exam.online_exam_system.model.ExamQuestionId;
import com.roima.exam.online_exam_system.model.QuestionBank;
import com.roima.exam.online_exam_system.repository.ExamQuestionRepository;
import com.roima.exam.online_exam_system.repository.QuestionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ExamQuestionService {

    private final ExamService examService;
    private final QuestionBankService questionBankService;
    ExamQuestionRepository examQuestionRepository;
    QuestionBankRepository questionBankRepository;

    @Autowired
    public ExamQuestionService(ExamQuestionRepository examQuestionRepository, QuestionBankRepository questionBankRepository, ExamService examService, QuestionBankService questionBankService) {
        this.examQuestionRepository = examQuestionRepository;
        this.questionBankRepository = questionBankRepository;
        this.examService = examService;
        this.questionBankService = questionBankService;
    }

    public boolean addQuestionsToExam(int examId, List<Integer> questionIds) {

        for (int id : questionIds) {

            QuestionBank question = questionBankService.findById(id);
            Exam exam = examService.findById(examId);

//          Create Composite PK
            ExamQuestionId examQueId = new ExamQuestionId();
            examQueId.setExamId(exam.getExamId());
            examQueId.setQuestionId(question.getQuestion_id());

//          Add entry of question_d and exam_id in exam_question table
            ExamQuestion newExamQuestion = new ExamQuestion();
            newExamQuestion.setId(examQueId);
            newExamQuestion.setQuestion(question);
            newExamQuestion.setExam(exam);

            examQuestionRepository.save(newExamQuestion);

        }
        return true;

    }

}
