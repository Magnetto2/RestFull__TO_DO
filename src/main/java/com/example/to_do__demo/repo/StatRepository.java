package com.example.to_do__demo.repo;

import com.example.to_do__demo.Entity.StatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<StatEntity, Long> {

}
