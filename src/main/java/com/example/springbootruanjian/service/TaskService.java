package com.example.springbootruanjian.service;

import com.example.springbootruanjian.entity.Task;
import com.example.springbootruanjian.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    //删除
    public void deleteTask(int id){taskRepository.deleteById(id);}

    public Task findById(int id){
        return taskRepository.findById(id).get();
    }
    //获取所有任务
    public List<Task> findall(){
        return taskRepository.findAll();
    }
}
