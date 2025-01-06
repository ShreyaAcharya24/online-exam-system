package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.OptionRequestDTO;
import com.roima.exam.online_exam_system.model.Option;
import com.roima.exam.online_exam_system.model.QuestionBank;
import com.roima.exam.online_exam_system.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public boolean addOptions(List<OptionRequestDTO> options, QuestionBank question) {

        if (options != null && !options.isEmpty()) {
            for (OptionRequestDTO optionDTO : options) {
                Option option = new Option();
                option.setOptionText(optionDTO.optionText());
                option.setIs_correct(optionDTO.isCorrect());
                option.setQuestion(question);
                optionRepository.save(option);
            }
            return true;
        }
        return false;
    }

    List<Option> getOptionsByQueId(int questionId){
        return optionRepository.findByQuestion_QuestionId(questionId);
    }
}
