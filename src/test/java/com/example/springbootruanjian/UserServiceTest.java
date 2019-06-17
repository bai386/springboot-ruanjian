package com.example.springbootruanjian;

import com.example.springbootruanjian.entity.User;
import com.example.springbootruanjian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void addUserTest(){
        User u = new User();
        u.setName("林孔仁");
        u.setJob("teacher");
        u.setPhone("123456");
        u.setUserName("2016");
        u.setPassword(passwordEncoder.encode("2016"));
        User u2=userService.addUser(u);
    }
    @Test
    public void updateUserTest(){
        User u = new User();
        u = userService.findById(2);
        u.setIntroduction("普通用户");
        User u2=userService.addUser(u);
    }
    @Test
    public void addAdminerTest(){
        userService.addAdminer(2);
    }
    @Test
    public void deleteAdminerTest(){
        userService.deleteAdminer(2);
    }
    @Test
    public void deleteUserTest(){
        userService.deleteUser(2);
    }
    @Test
    public void ListUserTest(){
        userService.listUser(1)
                .forEach(user -> {
                    log.debug(user.getName());
                });
    }
}

