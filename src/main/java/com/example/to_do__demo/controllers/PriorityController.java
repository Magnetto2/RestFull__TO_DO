package com.example.to_do__demo.controllers;

import com.example.to_do__demo.Entity.CategoryEntity;
import com.example.to_do__demo.Entity.PriorityEntity;
import com.example.to_do__demo.search.CategorySearchValues;
import com.example.to_do__demo.search.PrioritySearchValues;
import com.example.to_do__demo.service.PriorityService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/all")
    public List<PriorityEntity> findAll(){

         return  priorityService.findAll();

    }

    @PostMapping("/add")
    public ResponseEntity<PriorityEntity> add(@RequestBody PriorityEntity priorityEntity){

        if (priorityEntity.getId() != null && priorityEntity.getId() !=0){
            return new ResponseEntity("redundant:id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }


        if(priorityEntity.getTitle()==null || priorityEntity.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);

        }


        if(priorityEntity.getColor() == null && priorityEntity.getColor().trim().length()==0){
            return new ResponseEntity("Missed param : color", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(priorityService.add(priorityEntity));
    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody PriorityEntity priority){

        if(priority.getId() == null || priority.getId() == 0){
            return new ResponseEntity("missed param ID", HttpStatus.NOT_ACCEPTABLE);
        }

        if(priority.getTitle() == null && priority.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param : title", HttpStatus.NOT_ACCEPTABLE);
        }

        if(priority.getColor() != null && priority.getColor().trim().length()!=0){
            return new ResponseEntity("Missed param : color", HttpStatus.NOT_ACCEPTABLE);
        }



        PriorityEntity priorityEntity = priorityService.update(priority);
        return ResponseEntity.ok(priorityEntity);


    }


    @GetMapping("/id/{id}")
    public ResponseEntity<PriorityEntity> findById(@PathVariable Long id){
        PriorityEntity priority = null;

        try{
            priority = priorityService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(priority);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){



        try{
            priorityService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/search")
    public ResponseEntity<List<PriorityEntity>> search(@RequestBody PrioritySearchValues prioritySearchValues){
        return ResponseEntity.ok(priorityService.findByTitle(prioritySearchValues.getText()));
    }

}
