package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.model.Institute;
import com.roima.exam.online_exam_system.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/institutes")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @PostMapping("/add")
    public ResponseEntity<Institute> addInEntity(@RequestBody Institute institute){
        System.out.println("***Inside Post ****");
        Institute createdInstance = instituteService.addInstitute(institute);
        System.out.println("Response JSON: " + createdInstance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInstance);
    }



}
