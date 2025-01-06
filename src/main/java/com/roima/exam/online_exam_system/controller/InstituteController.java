package com.roima.exam.online_exam_system.controller;

import com.roima.exam.online_exam_system.dto.InstituteDTO;
import com.roima.exam.online_exam_system.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institutes")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    //    Add new Institute
    @PostMapping("/add")
    public InstituteDTO saveInstitute(@RequestBody InstituteDTO instituteDTO) {

        return this.instituteService.saveInstitute(instituteDTO);
    }

    //    Get All institutes
    @GetMapping("/get")
    public List<InstituteDTO> getAllInstitutes() {
        return instituteService.getAllInstitutes();
    }

    // Delete Institute
    @DeleteMapping("/delete/{id}")
    public void deleteInstitute(@PathVariable int id) {
        if (instituteService.deleteInstitute(id)) {
            System.out.println("**** Institute Deleted ******");
        } else {
            System.out.println("***** Institute Not Deleted ****");
        }

    }
}
