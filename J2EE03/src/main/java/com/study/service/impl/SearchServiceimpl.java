package com.study.service.impl;

import com.study.mapper.SearchMapper;
import com.study.pojo.PageBean;
import com.study.pojo.people;
import com.study.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceimpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;
    /*public List<people> get(String all){
        return searchMapper.get(all);
    }*/

    @Override
    public List<people> list() {
        return searchMapper.List();
    }

    @Override
    public List<people> pageShow(Integer size, Integer pageSize) {
        Integer start = (size - 1)*pageSize;
        return searchMapper.pageShow(start, pageSize);
    }

    @Override
    public void add(people student) {
        searchMapper.add(student);
    }

    @Override
    public void delete(Integer id) {
        searchMapper.delete(id);
    }

    @Override
    public int edit(people student) {
        return searchMapper.update(student);
    }

    @Override
    public List<people> search(String all) {
        return searchMapper.search(all);
    }

}
