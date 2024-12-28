package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.QuestionResponseDTO;
import com.roima.exam.online_exam_system.model.QuestionBank;
import com.roima.exam.online_exam_system.service.QuestionBankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionBankController {

    private final QuestionBankService questionBankService;

    public QuestionBankController(QuestionBankService questionBankService) {
        this.questionBankService = questionBankService;
    }

    @PostMapping("/add")
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody QuestionBank reqQuestion) {
        QuestionResponseDTO createdQue = questionBankService.addQuestion(reqQuestion);

        return new ResponseEntity<>(createdQue, HttpStatus.OK);
    }


}
