package com.example.springbootruanjian.service;

import com.example.springbootruanjian.entity.User;
import com.example.springbootruanjian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User getUser(String username){return userRepository.find(username);}
    //添加（修改）用户信息
    public User addUser(User u){
        User user=userRepository.save(u);
        userRepository.flush();
        userRepository.refresh(user);
        return user;
    }
    //添加管理员
    public void addAdminer(int uid){
        userRepository.addadminer(uid, 2);
    }
    //删除管理员
    public void deleteAdminer(int uid){
        userRepository.addadminer(uid, 1);
    }
    //删除指定用户
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    //按权限找人(普通教师1，管理员2，超级管理员3）
    public List<User> listUser(int aid){ return userRepository.listByauthority(aid);}
    //根据id找人
    public User findById(int id){
        return userRepository.findById(id).get();
    }
}
