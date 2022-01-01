package com.example.to_do__demo.search;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchValues {

    String title;
    Integer completed;
    Long priorityId;
    Long categoryId;

    private Integer pageNumber;
    private Integer  pageSize;


    private String sortColumn;
    private String sortDirection;



}
