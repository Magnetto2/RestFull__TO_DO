package com.example.to_do__demo.controllers;

import com.example.to_do__demo.Entity.StatEntity;
import com.example.to_do__demo.repo.StatRepository;
import com.example.to_do__demo.service.StatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;

public class StatController {

    private StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("{id}")
    public ResponseEntity<StatEntity> findById(@PathVariable Long id){

        StatEntity statEntity = null;

        try{
            statEntity = statService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(statEntity);


    }
}
