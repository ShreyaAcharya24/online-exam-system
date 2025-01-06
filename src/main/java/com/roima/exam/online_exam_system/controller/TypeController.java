package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.model.Type;
import com.roima.exam.online_exam_system.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
public class TypeController {

    private TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Type> addQuestionType(@RequestBody Type type) {
        Type createdType = typeService.addQuestionType(type);
        return new ResponseEntity<>(createdType, HttpStatus.CREATED);

    }
}
