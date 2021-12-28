package com.example.to_do__demo.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "category", schema = "to_do", catalog = "")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    private String title;
    @Basic
    @Column(name = "completed_count", nullable = true)
    private Long completedCount;
    @Basic
    @Column(name = "uncompleted_count", nullable = true)
    private Long uncompletedCount;



}
