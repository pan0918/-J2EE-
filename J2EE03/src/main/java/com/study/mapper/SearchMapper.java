package com.study.mapper;

import com.study.pojo.PageBean;
import org.apache.ibatis.annotations.*;
import com.study.pojo.people;

import java.util.List;

@Mapper
public interface SearchMapper {

    /*@Select("select * from people where number = #{all} or name = #{all} or classNumber = #{all}")
    List<people> get(String all);*/

    @Select("select * from people")
    List<people> List();

    @Insert("insert into people(number, name, classNumber, phoneNumber, email) values (#{number}, #{name}, #{classNumber}, #{phoneNumber}, #{email})")
    void add(people student);

    @Delete("delete from people where id = #{id}")
    void delete(Integer id);

    @Update("update people set number = #{number}, name = #{name}, classNumber = #{classNumber}, phoneNumber = #{phoneNumber}, email = #{email} where id = #{id}")
    int update(people student);

    @Select("select * from people where number = #{all} or name = #{all} or classNumber = #{all}")
    List<people> search(String all);

    @Select("select * from people limit #{start},#{pageSize}")
    List<people> pageShow(Integer start, Integer pageSize);
}
