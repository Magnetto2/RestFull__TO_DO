package com.example.to_do__demo.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stat", schema = "to_do")
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class StatEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "completed_total", nullable = true)
    private Long completedTotal;

    @Column(name = "uncompleted_total", nullable = true)
    private Long uncompletedTotal;



}
