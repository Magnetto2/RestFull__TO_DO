package com.example.to_do__demo.controllers;


import com.example.to_do__demo.Entity.CategoryEntity;
import com.example.to_do__demo.Entity.PriorityEntity;
import com.example.to_do__demo.repo.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> add(@RequestBody CategoryEntity category){

        if(category.getId() != null && category.getId() == 0){
            return new ResponseEntity("redundant:id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }


        if(category.getTitle() == null && category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param : title", HttpStatus.NOT_ACCEPTABLE);
        }


        CategoryEntity categoryEntity = categoryRepository.save(category);
        return ResponseEntity.ok(categoryEntity);
    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CategoryEntity category){

        if(category.getId() == null || category.getId() == 0){
            return new ResponseEntity("missed param ID", HttpStatus.NOT_ACCEPTABLE);
        }

        if(category.getTitle() == null && category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param : title", HttpStatus.NOT_ACCEPTABLE);
        }




        CategoryEntity categoryEntity = categoryRepository.save(category);
        return ResponseEntity.ok(categoryEntity);


    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        PriorityEntity priority = null;

        try{
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);

    }
}
