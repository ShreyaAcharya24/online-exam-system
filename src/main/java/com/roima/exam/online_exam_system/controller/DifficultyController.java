package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.model.Difficulty;
import com.roima.exam.online_exam_system.service.DifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/difficulties")
public class DifficultyController {

    @Autowired
    DifficultyService difficultyService;

    public DifficultyController(DifficultyService difficultyService) {
        this.difficultyService = difficultyService;
    }

    @PostMapping("/add")
    public ResponseEntity<Difficulty> addDifficulty(@RequestBody Difficulty difficulty){

        Difficulty createdDifficulty = difficultyService.addDifficulty(difficulty);
        return  new ResponseEntity<>(createdDifficulty, HttpStatus.CREATED);

    }
}
