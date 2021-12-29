package com.example.to_do__demo.controllers;

import com.example.to_do__demo.Entity.StatEntity;
import com.example.to_do__demo.repo.StatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;

public class StatController {

    private StatRepository statRepository;


    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<StatEntity> findById(@PathVariable Long id){

        StatEntity statEntity = null;

        try{
            statEntity = statRepository.findById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(statEntity);


    }
}
