package com.dy.springboot_data.mapper;

import com.dy.springboot_data.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 将该接口添加到mybatis管理的方法有二：
 *  1. 在接口上添加@Mapper注解，标记此接口为一个mapper实现类
 *  2. 在某个配置文件上添加@mapperScan注解，指定mapper接口所在的包
 */
//@Mapper
public interface PersonMapper {

    @Select("select * from person where id = #{id}")
    Person selectById(Integer id);
}
