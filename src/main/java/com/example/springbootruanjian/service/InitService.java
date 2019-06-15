package com.example.springbootruanjian.service;

import com.example.springbootruanjian.entity.User;
import com.example.springbootruanjian.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
public class InitService implements InitializingBean {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception{
        if(userRepository.count() == 0){
            User user = new User();
            user.setAuthority(User.SUPERADMIN_AUTHORITY);
            user.setName("白晓飞");
            user.setJob("superadminer");
            user.setIntroduction("超级管理员");
            user.setPhone("18845128815");
            user.setUserName("2016214386");
            user.setPassword(passwordEncoder.encode(user.getUserName()));
            userRepository.save(user);
        }
    }
}
