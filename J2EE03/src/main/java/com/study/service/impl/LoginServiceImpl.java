package com.study.service.impl;

import com.study.mapper.LoginMapper;
import com.study.pojo.manager;
import com.study.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public manager login(manager Manager){
        return loginMapper.login(Manager);
    }
}
