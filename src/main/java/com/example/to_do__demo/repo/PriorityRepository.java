package com.example.to_do__demo.repo;

import com.example.to_do__demo.Entity.CategoryEntity;
import com.example.to_do__demo.Entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity,Long> {

        @Query("SELECT c FROM PriorityEntity c where" +
                "(:title is null or :title='' or " +
                "lower(c.title) like lower(concat('%', :title,'%')))")
        List<PriorityEntity> findByTitle(@Param("title")String title);





        List<PriorityEntity> findAllByOrderByIdAsc();
}
