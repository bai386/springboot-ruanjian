package com.example.springbootruanjian;

import com.example.springbootruanjian.entity.Exam;
import com.example.springbootruanjian.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExamServiceTest {
    @Autowired
    private ExamService examService;

    @Test
    public void addExamTest(){
        Exam e = new Exam();
        e.setCourseName("系统程序设计");
        e.setSite("丹青楼205");
        e.setNeedPeople(3);
        e.setStartTime(LocalDateTime.parse("2019-06-20T08:30:00"));
        e.setFinishTime(LocalDateTime.parse("2019-06-20T10:30"));
        //e.setStartTime(2019-6-20 08:30:00);

        Exam e2 = examService.addExam(e);
    }
    @Test
    public void updateExamTest(){
        Exam e = null;
        e = examService.findById(1);
        e.setNeedPeople(2);
        Exam e2 = examService.addExam(e);
    }
    @Test
    public void ListExamText(){
        examService.listExam()
                .forEach(exam -> {
                    log.debug(exam.getCourseName());
                });
    }
}
