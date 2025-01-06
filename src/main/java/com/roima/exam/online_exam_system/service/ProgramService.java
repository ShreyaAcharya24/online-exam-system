package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.ProgramAns;
import com.roima.exam.online_exam_system.model.QuestionBank;
import com.roima.exam.online_exam_system.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    private ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public boolean addProgramAns(String requestAns, QuestionBank question) {

        if (requestAns != null && !requestAns.isEmpty()) {

            ProgramAns ans = new ProgramAns();
            ans.setAnsText(requestAns);
            ans.setQuestion(question);
            return true;
        }

        return false;
    }

}
