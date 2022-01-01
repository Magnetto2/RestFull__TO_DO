package com.example.to_do__demo.service;


import com.example.to_do__demo.Entity.TaskEntity;
import com.example.to_do__demo.repo.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<TaskEntity> findAll() {
        return repository.findAll();
    }

    public TaskEntity add(TaskEntity task) {
        return repository.save(task); // метод save обновляет или создает новый объект, если его не было
    }

    public TaskEntity update(TaskEntity task){
        return repository.save(task); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }


    public Page findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging){
        return repository.findByParams(text, completed, priorityId, categoryId, paging);
    }

    public TaskEntity findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }


}
