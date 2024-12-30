package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.QuestionRequestDTO;
import com.roima.exam.online_exam_system.dto.QuestionResponseDTO;
import com.roima.exam.online_exam_system.model.*;
import com.roima.exam.online_exam_system.repository.QuestionBankRepository;
import com.roima.exam.online_exam_system.repository.UserRepository;
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
    private OptionService optionService;
    private ProgramService programService;

    public QuestionBankService(TypeService typeService, CategoryService categoryService, DifficultyService difficultyService, UserRepository userRepository, UserService userService, AdminService adminService, QuestionBankRepository questionBankRepository, OptionService optionService, ProgramService programService) {
        this.typeService = typeService;
        this.categoryService = categoryService;
        this.difficultyService = difficultyService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.adminService = adminService;
        this.questionBankRepository = questionBankRepository;
        this.optionService = optionService;
        this.programService = programService;
    }
//    Add Question

    public QuestionResponseDTO addQuestion(QuestionRequestDTO requestQuestion) {

        QuestionBank newquestion = new QuestionBank();
        newquestion.setQuestion_text(requestQuestion.questionText());
        newquestion.setMarks(requestQuestion.marks());

//        Retrieve FK values

        Type type = typeService.findById(requestQuestion.type().getTypeId());
        System.out.println("***** " + requestQuestion.type());
        System.out.println("*****-- " + requestQuestion.type().getTypeName());
        System.out.println("*****-- " + type);
        newquestion.setType(type);

        Category category = categoryService.findById(requestQuestion.category().getCategoryId());
        newquestion.setCategory(category);

        Difficulty difficulty = difficultyService.findById(requestQuestion.difficulty().getLevelId());
        newquestion.setDifficulty(difficulty);

        User user = userService.findById(requestQuestion.createdBy().getUser_id());

        newquestion.setCreatedBy(user);
        newquestion.setUpdateBy(user);

        questionBankRepository.save(newquestion);

        if (newquestion.getType().getTypeName().equalsIgnoreCase("MCQ")) {

            if (optionService.addOptions(requestQuestion.options(), newquestion)){
                System.out.println(" ***** Option Added ******");
            }
            else {
                System.out.println(" *** Option addition failed *****");
            }

        } else if (newquestion.getType().getTypeName().equalsIgnoreCase("Programming")) {

            if(programService.addProgramAns(requestQuestion.progAns(), newquestion)){
                System.out.println(" ***** Program Ref ans added ******");
            }
            else {
                System.out.println(" *** Ref ans addition failed *****");
            }

        }


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
