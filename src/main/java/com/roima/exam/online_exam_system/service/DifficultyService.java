package com.roima.exam.online_exam_system.service;


import com.roima.exam.online_exam_system.model.Difficulty;
import com.roima.exam.online_exam_system.repository.DifficultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DifficultyService {

    private DifficultyRepository difficultyRepository;

    @Autowired
    public DifficultyService(DifficultyRepository difficultyRepository) {
        this.difficultyRepository = difficultyRepository;
    }

    public Difficulty addDifficulty(Difficulty difficulty) {
        return difficultyRepository.save(difficulty);
    }

    public Difficulty findById(int id) {
        return difficultyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Difficulty not Found"));

    }
}
