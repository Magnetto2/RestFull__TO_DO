package com.example.to_do__demo.repo;


import com.example.to_do__demo.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {


    @Query("SELECT c FROM CategoryEntity c where" +
            "(:title is null or :title='' or " +
            "lower(c.title) like lower(concat('%', :title,'%')))")
    List<CategoryEntity> findByTitle(@Param("title")String title);

    List<CategoryEntity> findAllByOrderByTitleAsc();

}
