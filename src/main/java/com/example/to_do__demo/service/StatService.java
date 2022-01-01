package com.example.to_do__demo.service;


import com.example.to_do__demo.Entity.StatEntity;
import com.example.to_do__demo.repo.StatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatService {

    private final StatRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public StatEntity findById(Long id){
        return repository.findById(id).get();
    }

}
