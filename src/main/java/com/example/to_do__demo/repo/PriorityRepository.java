package com.example.to_do__demo.repo;

import com.example.to_do__demo.Entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity,Long> {

}
