package com.example.springbootruanjian.controller;

import com.example.springbootruanjian.component.EncryptorComponent;
import com.example.springbootruanjian.entity.User;
import com.example.springbootruanjian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    private static final String TEACHER_ROLE = "bb63e5f7e0f2ffae845c";
    private static final String ADMIN_ROLE = "6983f953b49c88210cb9";
    private static final String SUPERADMIN_ROLE = "dfd6581erce48ty8612f";
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response){
        Optional.ofNullable(userService.getUser(user.getUserName()))
                .ifPresentOrElse(u -> {
                    if(!passwordEncoder.matches(user.getPassword(), u.getPassword())){
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                    }
                    Map map = Map.of("uid", u.getId(), "aid", u.getAuthority());
                    //生成加密token
                    String token = encryptorComponent.encrypt(map);
                    //在header创建自定义权限
                    response.setHeader("token", token);
                    String role = null;
                    if(u.getAuthority() == User.USER_AUTHORITY) {
                        role = TEACHER_ROLE;
                    }
                    if(u.getAuthority() == User.ADMIN_AUTHORITY) {
                        role = ADMIN_ROLE;
                    }
                    if(u.getAuthority() == User.SUPERADMIN_AUTHORITY){
                        role = SUPERADMIN_ROLE;
                    }
                    response.setHeader("role", role);
                }, () ->{
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                });
    }
}
