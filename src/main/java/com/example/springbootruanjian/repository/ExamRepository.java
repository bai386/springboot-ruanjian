package com.example.springbootruanjian.repository;

import com.example.springbootruanjian.entity.Exam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ExamRepository extends CustomizedRepository<Exam,Integer> {
    @Query("select e from Exam e where e.courseName=: coursename")
    Exam find(@Param("coursename") String coursename);
    @Transactional
    @Modifying
    @Query("update Exam e set e.courseName=:cName where e.id=:id")
    void updateExam(@Param("cName") String cName);
}
