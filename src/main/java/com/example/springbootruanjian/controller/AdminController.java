package com.example.springbootruanjian.controller;

import com.example.springbootruanjian.entity.*;
import com.example.springbootruanjian.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskReplyService taskReplyService;
    @Autowired
    private InvigilateService invigilateService;

    @PostMapping("/exam")
    public Map postExam(@RequestBody Exam exam,@RequestAttribute int eid){
        examService.addExam(exam);
        examService.deleteExam(eid);
        return Map.of("exam", examService.listExam());
    }
    @PostMapping("/exam/{eid}/invigilate")
    public Map postInvigilate(@PathVariable int eid, @RequestBody Invigilate invigilate,@RequestBody Exam exam){
        if(invigilateService.countExam(eid) < exam.getNeedPeople()){
            invigilateService.addInvigilate(invigilate);
        }
        return Map.of("invigilate", invigilateService.listexamInvigilate(eid));
    }
    @PostMapping("/user")
    public Map postUser(@RequestBody User user,@RequestAttribute int uid){
        userService.addUser(user);
        userService.addAdminer(uid);
        userService.deleteUser(uid);
        return Map.of("user", userService.listUser(1), "user", userService.listUser(2));
    }
    @PostMapping("/task")
    public Map postTask(@RequestBody Task task,@RequestAttribute int tid){
        taskService.addTask(task);
        taskService.deleteTask(tid);
        return Map.of("task", taskService.findall());
    }
    @PostMapping("/task/{tid}/taskreply")
    public Map postTaskReply(@PathVariable int tid, @RequestBody TaskReply taskReply,@RequestBody Exam exam){
        taskReplyService.addTaskReply(taskReply);
        return Map.of("taskreply", taskReplyService.taskList(tid));
    }
}
