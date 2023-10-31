package com.study.service;

import com.study.pojo.PageBean;
import com.study.pojo.people;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SearchService {
    /*List<people> get(String all);*/

    List<people> list();

    List<people> pageShow(Integer size, Integer pageSize);

    void add(people student);

    void delete(Integer id);

    int edit(people student);

    List<people> search(String all);
}
