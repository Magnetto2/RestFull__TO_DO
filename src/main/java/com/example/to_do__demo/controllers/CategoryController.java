package com.example.to_do__demo.controllers;


import com.example.to_do__demo.Entity.CategoryEntity;
import com.example.to_do__demo.repo.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test")
    public List<CategoryEntity> test(){
         List<CategoryEntity> list = categoryRepository.findAll();

         return list;
    }
}
