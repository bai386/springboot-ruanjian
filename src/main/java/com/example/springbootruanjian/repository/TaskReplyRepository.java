package com.example.springbootruanjian.repository;

import com.example.springbootruanjian.entity.TaskReply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskReplyRepository extends CustomizedRepository<TaskReply,Integer> {
    @Query("select tr from TaskReply tr where tr.id=: id")
    TaskReply find(@Param("id") int id);

    @Query("select tr from TaskReply tr where tr.teacher.id = tid")
    List<TaskReply> findByteacher(@Param("tid") int tid);

    @Query("select tr from TaskReply tr where tr.task.id = tid")
    List<TaskReply> findBytask(@Param("tid") int tid);

    @Transactional
    @Modifying
    @Query("update TaskReply tr set tr.review = :review where tr.task.id=tid")
    void replyTask(@Param("tid") int tid,@Param("review") String review);
}
