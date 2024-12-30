package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.Type;
import com.roima.exam.online_exam_system.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {

        this.typeRepository = typeRepository;
    }

    public Type addQuestionType(Type questionType) {

        return typeRepository.save(questionType);
    }

    public Type findById(int id){
        return typeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Question Type not Found") );

    }


}
