package com.roima.exam.online_exam_system.service;


import com.roima.exam.online_exam_system.model.Difficulty;
import com.roima.exam.online_exam_system.repository.CategoryRepository;
import com.roima.exam.online_exam_system.repository.DifficultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DifficultyService
{
    @Autowired
    private DifficultyRepository difficultyRepository;

    public Difficulty addDifficulty(Difficulty difficulty){

        return  difficultyRepository.save(difficulty);

    }
}
