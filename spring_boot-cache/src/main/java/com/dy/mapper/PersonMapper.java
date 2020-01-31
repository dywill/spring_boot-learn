package com.dy.mapper;

import com.dy.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PersonMapper {

    @Select("select * from person where id = #{id}")
    Person selectById(Integer id);

    @Update("update person set name = #{name}, gender = #{gender} where id = #{id}")
    int updateById(Person person);
}
