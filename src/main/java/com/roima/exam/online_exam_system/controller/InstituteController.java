package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.InstituteRequestDTO;
import com.roima.exam.online_exam_system.dto.InstituteResponseDTO;
import com.roima.exam.online_exam_system.model.Institute;
import com.roima.exam.online_exam_system.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/institutes")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @PostMapping("/add")
    public ResponseEntity<InstituteResponseDTO> addInstitute(@RequestBody InstituteRequestDTO instituteRequestDTO){
        System.out.println("***Inside Post ****");

        Institute institute = new Institute();
        institute.setInstitute_name(instituteRequestDTO.getInstitute_name());
        institute.setAddress(instituteRequestDTO.getAddress());
        institute.setContact(instituteRequestDTO.getContact());

        Institute createdInstitute = instituteService.addInstitute(institute);

        InstituteResponseDTO instituteResponseDTO = new InstituteResponseDTO();
        instituteResponseDTO.setInstitute_name(createdInstitute.getInstitute_name());
        instituteResponseDTO.setInstiute_id(createdInstitute.getInstitute_id());

//        Map<String, Object> response = new HashMap<>();
//        response.put("status", "success");
//        response.put("code", HttpStatus.CREATED.value());
//        response.put("message", "Institute created successfully");
//        response.put("data", instituteResponseDTO);
//        response.put("timestamp", java.time.Instant.now().toString());

        // Return the response wrapped in ResponseEntity
        return new ResponseEntity<>(instituteResponseDTO, HttpStatus.CREATED);

       }
}
