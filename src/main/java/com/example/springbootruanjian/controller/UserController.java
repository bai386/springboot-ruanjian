package com.example.springbootruanjian.controller;

import com.example.springbootruanjian.entity.*;
import com.example.springbootruanjian.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
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

    @GetMapping("/main")
    public Map getMain(@RequestAttribute int uid, @RequestAttribute int aid){
        List<Invigilate> invigilates = null;
        List<TaskReply> taskReplies = null;
        if (aid == User.USER_AUTHORITY){
            invigilates = invigilateService.listteacherInvigilate(uid);
            taskReplies = taskReplyService.teacherTasklist(uid);
        }
        if(aid == User.ADMIN_AUTHORITY||aid == User.SUPERADMIN_AUTHORITY){
            invigilates = invigilateService.findall();
            taskReplies = taskReplyService.findall();
        }
        return Map.of("invigilates", invigilates, "taskReplies", taskReplies);
    }
    @GetMapping("/exam/{eid}")
    public Map getExam(@PathVariable int eid,@RequestAttribute int uid,@RequestAttribute int aid){
        Exam exam = null;
        List<Exam> exams = null;
        if (aid == User.USER_AUTHORITY){
            exam = examService.findById(eid);
        }
        if (aid == User.ADMIN_AUTHORITY||aid == User.SUPERADMIN_AUTHORITY){
            exams = examService.listExam();
        }
        return  Map.of("exam", exam, "exams", exams);
    }
    @GetMapping("/task/{tid}")
    public Map getTask(@PathVariable int tid,@RequestAttribute int aid){
        Task task = null;
        List<Task> tasks = null;
        if (aid == User.USER_AUTHORITY){
            task = taskService.getTask(tid);
        }
        if (aid == User.ADMIN_AUTHORITY||aid == User.SUPERADMIN_AUTHORITY){
            tasks = taskService.findall();
        }
        return Map.of("task", task, "tasks", tasks);
    }
    @PostMapping("task/{tid}/taskreply")
    public Map posttaskReply(@PathVariable int tid,@RequestAttribute String review,
                             @RequestBody TaskReply taskReply,@RequestBody Task task){
        taskReplyService.ReplyTask(tid,review);
        if(taskReply.getReview() != null && taskReply.getUpdatetime().isBefore(task.getDeadline())){
            taskReply.setAchive(1);
        }
        if(taskReply.getReview() != null && taskReply.getUpdatetime().isAfter(task.getDeadline())){
            taskReply.setAchive(2);
        }
        return Map.of("taskreply", taskReplyService.taskList(tid));
    }
}
