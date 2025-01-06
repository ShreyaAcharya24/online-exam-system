package com.roima.exam.online_exam_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/stu-login")
    public String loginPage(){
        return "redirect:/login.html";
    }

    @GetMapping("/exam")
    public String showExamPage(){
        return "redirect:/exam.html";
    }
}
