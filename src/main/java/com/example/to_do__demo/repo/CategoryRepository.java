package com.example.to_do__demo.repo;


import com.example.to_do__demo.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {



}
