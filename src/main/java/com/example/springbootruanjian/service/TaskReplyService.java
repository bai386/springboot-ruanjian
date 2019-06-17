package com.example.springbootruanjian.service;

import com.example.springbootruanjian.entity.TaskReply;
import com.example.springbootruanjian.repository.TaskReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskReplyService {
    @Autowired
    private TaskReplyRepository taskReplyRepository;

    public TaskReply addTaskReply(TaskReply tr){
        TaskReply taskReply = taskReplyRepository.save(tr);
        taskReplyRepository.flush();
        taskReplyRepository.refresh(taskReply);
        return taskReply;
    }
    //根据ID找指定回复
    public TaskReply findById(int id){
        return taskReplyRepository.findById(id).get();
    }
    //获取指定教师的回复情况
    public List<TaskReply> teacherTasklist(int tid){
        return taskReplyRepository.findByteacher(tid);
    }
    //获取指定任务的回复情况
    public List<TaskReply> taskList(int tid){
        return taskReplyRepository.findBytask(tid);
    }
    //所有
    public List<TaskReply> findall(){return taskReplyRepository.findAll();}
    //回复指定任务消息
    public void ReplyTask(int tid, String review){
        taskReplyRepository.replyTask(tid,review);
    }
}
