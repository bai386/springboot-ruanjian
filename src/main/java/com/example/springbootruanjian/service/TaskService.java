package com.example.springbootruanjian.service;

import com.example.springbootruanjian.entity.Task;
import com.example.springbootruanjian.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    //获取指定任务
    public Task getTask(int tid){return taskRepository.find(tid);}

    public Task addTask(Task t){
        Task task=taskRepository.save(t);
        taskRepository.flush();
        taskRepository.refresh(task);
        return task;
    }

    public void deleteTask(int id){taskRepository.deleteById(id);}
}
