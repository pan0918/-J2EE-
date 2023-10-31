package com.study.mapper;

import com.study.pojo.manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from manager where name = #{name} and password = #{password}")
    manager login(manager Manager);
}
