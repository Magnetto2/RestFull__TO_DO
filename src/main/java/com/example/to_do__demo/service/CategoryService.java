package com.example.to_do__demo.service;


import com.example.to_do__demo.Entity.CategoryEntity;
import com.example.to_do__demo.repo.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }

    public CategoryEntity add(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity update(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }


    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public  List<CategoryEntity> findByTitle(String text){
        return categoryRepository.findByTitle(text);
    }

    public CategoryEntity findById(Long id){
        return  categoryRepository.findById(id).get();
    }

    public List<CategoryEntity> findAllByOrderByTitleAsc(){
        return categoryRepository.findAllByOrderByTitleAsc();
    }





}
