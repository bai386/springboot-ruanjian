package com.example.springbootruanjian.repository;

import com.example.springbootruanjian.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CustomizedRepository<Task,Integer> {
    @Query("select t from Task t where t.id=: tid")
    Task find(@Param("tid") int tid);
}
