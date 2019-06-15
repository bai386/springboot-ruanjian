package com.example.springbootruanjian.service;

import com.example.springbootruanjian.entity.Exam;
import com.example.springbootruanjian.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    //根据考试课程找考试
    public Exam getExam(String coursename){
        return examRepository.find(coursename);
    }

    public Exam addExam(Exam e){
        Exam exam = examRepository.save(e);
        examRepository.flush();
        examRepository.refresh(exam);
        return exam;
    }
     public void deleteExam(int id){
        examRepository.deleteById(id);
     }
     //所有考试
     public List<Exam> listExam(){
        return examRepository.findAll();
     }
     //根据Id查询考试
    public Exam findById(int id){
       return examRepository.findById(id).get();
    }
}
