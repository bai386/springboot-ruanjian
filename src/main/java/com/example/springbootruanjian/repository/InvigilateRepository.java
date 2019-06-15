package com.example.springbootruanjian.repository;

import com.example.springbootruanjian.entity.Invigilate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvigilateRepository extends CustomizedRepository<Invigilate,Integer> {
    @Query("select i from Invigilate i where i.exam.id=:eid")
    List<Invigilate> examlist(@Param("eid") int eid);

    @Query("select i from Invigilate i where i.teacher.id=:tid")
    List<Invigilate> teacherexamlist(@Param("tid") int tid);

    @Query("select count(i) from Invigilate i where i.teacher.id=:tid")
    int findcount(@Param("tid") int tid);

    @Query("select i from Invigilate i where i.exam.id=:eid and i.teacher.id=:tid")
    Invigilate find(@Param("eid") int eid,@Param("tid") int tid);

}
