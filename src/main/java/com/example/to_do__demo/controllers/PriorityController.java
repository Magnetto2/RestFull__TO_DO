package com.example.to_do__demo.controllers;

import com.example.to_do__demo.Entity.PriorityEntity;
import com.example.to_do__demo.repo.PriorityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;


    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<PriorityEntity> test(){

        List<PriorityEntity> list = priorityRepository.findAll();
        System.out.println("list = " + list);

        return list;
    }



}
