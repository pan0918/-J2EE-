package com.study.controller;

import com.study.pojo.PageBean;
import com.study.pojo.people;
import com.study.pojo.Result;
import com.study.service.SearchService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private SearchService searchService;


    //显示所有的学生
    @GetMapping(value = "/info", produces = {"application/json;charset=UTF-8"})
    public List<people> list(){
        log.info("显示所有学生信息");
        List<people> students = searchService.list();
        return students;
    }

    //分页显示
    @GetMapping(value = "show/{size}/{pageSize}")
    public List<people> pageShow(@PathVariable Integer size,@PathVariable Integer pageSize){
        log.info("分页显示：{}，{}",size,pageSize);
        List<people> all = searchService.pageShow(size, pageSize);
        return all;
    }

    //增加学生
    @PostMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public void add(@RequestBody people student){
        log.info("增加学生：{}",student);
        searchService.add(student);
    }

    //删除学生
    @PostMapping(value = "remove/{id}", produces = {"application/json;charset=UTF-8"})
    public Result delete(@PathVariable("id") Integer id){
        log.info("删除学生的id为：{}",id);
        searchService.delete(id);
        return Result.success(id);
    }

    //修改学生信息
    @PostMapping(value = "/edit", produces = {"application/json;charset=UTF-8"})
    public void edit(@RequestBody people student){
        log.info("修改的学生信息：{}", student);
        searchService.edit(student);
    }

    //查询学生信息
    @GetMapping(value = "get/{all}", produces = {"application/json;charset=UTF-8"})
    public List<people> get(@PathVariable String all){
        log.info("查询的学生信息为：{}", all);
        List<people> result = searchService.search(all);
        return result;
    }
}
