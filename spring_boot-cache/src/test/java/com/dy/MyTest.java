package com.dy;

import com.dy.entity.Person;
import com.dy.mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void test01(){
        Person person = personMapper.selectById(1);
        System.out.println(person);
    }
}
