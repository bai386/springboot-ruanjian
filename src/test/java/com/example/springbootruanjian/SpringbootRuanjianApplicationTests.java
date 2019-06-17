package com.example.springbootruanjian;

import com.example.springbootruanjian.entity.Task;
import com.example.springbootruanjian.entity.TaskReply;
import com.example.springbootruanjian.entity.User;
import com.example.springbootruanjian.service.TaskReplyService;
import com.example.springbootruanjian.service.TaskService;
import com.example.springbootruanjian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootRuanjianApplicationTests {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskReplyService taskReplyService;
    @Autowired
    private UserService userService;
    @Test
    public void addTaskTest(){
        Task task = new Task();
        task.setTaskName("程序设计");
        task.setIntroduction("按照要求完成程序设计");
        task.setDeadline(LocalDateTime.parse("2019-06-20T08:30"));
        Task task1 = taskService.addTask(task);
    }
    @Test
    public void addTaskReply(){
        TaskReply taskReply = new TaskReply();
        Task task = taskService.findById(1);
        User user = userService.findById(2);
        taskReply.setTask(task);
        taskReply.setTeacher(user);
        TaskReply taskReply1 = taskReplyService.addTaskReply(taskReply);
    }
}
