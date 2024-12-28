package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.model.Category;
import com.roima.exam.online_exam_system.model.Type;
import com.roima.exam.online_exam_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

   public Category addCategory(Category category){
       return  categoryRepository.save(category);
   }

    public Category findById(int id){
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not Found") );

    }
}
