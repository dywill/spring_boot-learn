package com.dy.springboot_data;

import com.dy.springboot_data.entity.Department;
import com.dy.springboot_data.entity.Person;
import com.dy.springboot_data.mapper.DepartmentMapper;
import com.dy.springboot_data.mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDataJdbcTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test01(){
        Connection connection = null;
        try {

            connection = dataSource.getConnection();
            System.out.println(dataSource.getClass().getName());
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void test02() {
        Person person = personMapper.selectById(1);
        System.out.println(person);

        Department department = departmentMapper.selectById(1);
        System.out.println(department);

    }

}
