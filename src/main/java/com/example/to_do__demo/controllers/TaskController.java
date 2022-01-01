package com.example.to_do__demo.controllers;


import com.example.to_do__demo.Entity.TaskEntity;
import com.example.to_do__demo.search.TaskSearchValues;
import com.example.to_do__demo.service.TaskService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task") // базовый адрес
public class TaskController {

    private final TaskService taskService; // сервис для доступа к данным (напрямую к репозиториям не обращаемся)


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // получение всех данных
    @GetMapping("/all")
    public ResponseEntity<List<TaskEntity>> findAll() {


        return ResponseEntity.ok(taskService.findAll());
    }


    @PostMapping("/add")
    public ResponseEntity<TaskEntity> add(@RequestBody TaskEntity taskEntity) {

        if (taskEntity.getId() != null && taskEntity.getId() != 0) {
            return new ResponseEntity("redundant:id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }


        if (taskEntity.getTitle() == null || taskEntity.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);

        }


        return ResponseEntity.ok(taskService.add(taskEntity));
    }


    // обновление
    @PutMapping("/update")
    public ResponseEntity<TaskEntity> update(@RequestBody TaskEntity task) {


        // проверка на обязательные параметры
        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        // save работает как на добавление, так и на обновление
        taskService.update(task);

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)

    }


    // удаление по id
    // параметр id передаются не в BODY запроса, а в самом URL
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }


    // получение объекта по id
    @GetMapping("/id/{id}")
    public ResponseEntity<TaskEntity> findById(@PathVariable Long id) {
        TaskEntity task = null;

        try {
            task = taskService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(task);
    }


    @PostMapping("/search")
    public ResponseEntity<Page<TaskEntity>> search(@RequestBody TaskSearchValues taskSearchValues) {

        // исключить NullPointerException
        String text = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;

        // конвертируем Boolean в Integer
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;

        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;

        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;

        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;


        // подставляем все значения

        // объект сортировки
        Sort sort = Sort.by(direction, sortColumn);

        // объект постраничности
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        // результат запроса с постраничным выводом
        Page result = taskService.findByParams(text, completed, priorityId, categoryId, pageRequest);

        // результат запроса
        return ResponseEntity.ok(result);
    }

}
