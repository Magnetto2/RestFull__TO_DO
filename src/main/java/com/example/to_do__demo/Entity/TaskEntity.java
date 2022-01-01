package com.example.to_do__demo.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "task", schema = "to_do")
public class TaskEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "title", length = 100)
    private String title;
    @Basic
    @Column(name = "completed")
    private Integer completed;
    @Basic
    @Column(name = "date")
    private Date date;


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")

    private CategoryEntity category;


    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private PriorityEntity priority;




}
