package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.Category;
import com.roima.exam.online_exam_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

   public Category addCategory(Category category){
       return  categoryRepository.save(category);
   }
}
