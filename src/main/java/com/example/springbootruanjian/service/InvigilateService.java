
package com.example.springbootruanjian.service;

import com.example.springbootruanjian.entity.Invigilate;
import com.example.springbootruanjian.repository.InvigilateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvigilateService {
    @Autowired
    private InvigilateRepository invigilateRepository;
    //获取指定考试的监考信息；
    public List<Invigilate> listexamInvigilate(int eid){
        return invigilateRepository.examlist(eid);
    }
    //获取指定教师的监考信息；
    public List<Invigilate> listteacherInvigilate(int tid){
        return invigilateRepository.teacherexamlist(tid);
    }
    //获取教师监考数
    public int counttInvigilate(int tid){
        return invigilateRepository.findcount(tid);
    }
    //添加监考信息
    public Invigilate addInvigilate(Invigilate i){
        Invigilate invigilate=invigilateRepository.save(i);
        invigilateRepository.flush();
        invigilateRepository.refresh(invigilate);
        return invigilate;
    }
    //删除
    public void deleteInvigilate(int id){invigilateRepository.deleteById(id);}
}

