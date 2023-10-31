package com.study.controller;

import com.study.pojo.Result;
import com.study.pojo.manager;
import com.study.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public boolean login(@RequestBody manager Manager){
        log.info("管理员登录:{}",Manager);
        manager manager1 = loginService.login(Manager);

        if(manager1 != null){
            return true;
        }
        return false;
    }
}
