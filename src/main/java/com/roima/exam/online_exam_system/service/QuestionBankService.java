package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.QuestionResponseDTO;
import com.roima.exam.online_exam_system.model.*;
import com.roima.exam.online_exam_system.repository.QuestionBankRepository;
import com.roima.exam.online_exam_system.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionBankService {

    private final TypeService typeService;
    private final CategoryService categoryService;
    private final DifficultyService difficultyService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final AdminService adminService;
    private QuestionBankRepository questionBankRepository;

    public QuestionBankService(QuestionBankRepository questionBankRepository, TypeService typeService, CategoryService categoryService, DifficultyService difficultyService, UserRepository userRepository, UserService userService, AdminService adminService) {
        this.questionBankRepository = questionBankRepository;
        this.typeService = typeService;
        this.categoryService = categoryService;
        this.difficultyService = difficultyService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.adminService = adminService;
    }

//    Add Question

    public QuestionResponseDTO addQuestion(QuestionBank requestQuestion) {

        QuestionBank newquestion = new QuestionBank();
        newquestion.setQuestion_text(requestQuestion.getQuestion_text());
        newquestion.setMarks(requestQuestion.getMarks());

//        Retrieve FK values

        Type type = typeService.findById(requestQuestion.getType().getTypeId());
        System.out.println("***** " + requestQuestion.getType());
        System.out.println("*****-- " + requestQuestion.getType().getTypeId());
        System.out.println("*****-- " + type);
        newquestion.setType(type);

        Category category = categoryService.findById(requestQuestion.getCategory().getCategoryId());
        newquestion.setCategory(category);

        Difficulty difficulty = difficultyService.findById(requestQuestion.getDifficulty().getLevelId());
        newquestion.setDifficulty(difficulty);

        User user = userService.findById(requestQuestion.getCreatedBy().getUser_id());

        newquestion.setCreatedBy(user);

        questionBankRepository.save(newquestion);


//        Prepare Response DTO
        QuestionResponseDTO responseDTO = new QuestionResponseDTO(
                newquestion.getQuestion_text(),
                newquestion.getMarks(),
                newquestion.getCategory().getCategoryName(),
                newquestion.getType().getTypeName(),
                newquestion.getDifficulty().getLevelName(),
                newquestion.getCreatedBy().getEmail(),
                newquestion.getCreatedBy().getRole(),
                newquestion.getCreatedAt());

        return responseDTO;
    }


}
