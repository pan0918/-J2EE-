package com.study.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Locale;

//分页封装类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Integer page; //总条数
    private Integer size;  //每页条数
}
