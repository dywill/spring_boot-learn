package com.dy.mapper;

import com.dy.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PersonMapper {

    @Select("select * from person where id = #{id}")
    Person selectById(Integer id);
}
