package com.study.service;

import com.study.pojo.manager;
import org.springframework.stereotype.Component;

@Component
public interface LoginService {
    manager login(manager Manager);
}
