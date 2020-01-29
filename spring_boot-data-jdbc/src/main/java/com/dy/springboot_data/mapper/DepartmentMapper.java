package com.dy.springboot_data.mapper;

import com.dy.springboot_data.entity.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {

    Department selectById(@Param("id") Integer id);

}
