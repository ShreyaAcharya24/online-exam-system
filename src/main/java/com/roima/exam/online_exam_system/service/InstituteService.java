package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.Institute;
import com.roima.exam.online_exam_system.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;

    public Institute addInstitute(Institute institute){
        return instituteRepository.save(institute);
    }


}
