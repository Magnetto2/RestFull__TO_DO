package com.example.to_do__demo.repo;

import com.example.to_do__demo.Entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    // учитываем, что параметр может быть null или пустым
    @Query("SELECT p FROM TaskEntity p where " +
            "(:text is null or lower(p.title) like lower(concat('%', :text,'%'))) and" +
            "(:completed is null or p.completed=:completed) and " +
            "(:priorityId is null or p.priority.id=:priorityId) and " +
            "(:categoryId is null or p.category.id=:categoryId)"
    )
    Page<TaskEntity> findByParams(@Param("text") String text,
                                  @Param("completed") Integer completed,
                                  @Param("priorityId") Long priorityId,
                                  @Param("categoryId") Long categoryId,
                                  Pageable pageable
    );

}

//@Repository
//public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
//
//
//    @Query("select t from TaskEntity t where " +
//            "(:title is null or :title='' or lower(t.title) like  lower(concat('%', :title,'%'))) and" +
//            "(:completed is null or t.completed = :completed) and" +
//            ":priorityId is null or t.priority= :priorityId and" +
//            ":categoryId is null or t.category = :categoryId"
//    )
//    List<TaskEntity> findAllByParams(@Param("title")String title, @Param("completed")Integer completed,
//                                     @Param("priorityId")Long priorityId, @Param("categoryId")Long categoryId);
//
//
//
//
//}
