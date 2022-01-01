package com.example.to_do__demo.service;


import com.example.to_do__demo.Entity.PriorityEntity;
import com.example.to_do__demo.repo.PriorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<PriorityEntity> findAll(){
        return priorityRepository.findAll();
    }

    public PriorityEntity add(PriorityEntity priority){
        return priorityRepository.save(priority);
    }

    public PriorityEntity update(PriorityEntity priority){
        return priorityRepository.save(priority);
    }


    public void deleteById(Long id){
        priorityRepository.deleteById(id);
    }

    public  List<PriorityEntity> findByTitle(String text){
        return priorityRepository.findByTitle(text);
    }

    public PriorityEntity findById(Long id){
        return  priorityRepository.findById(id).get();
    }






}
