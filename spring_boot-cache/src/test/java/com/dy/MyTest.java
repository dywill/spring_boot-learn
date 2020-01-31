package com.dy;

import com.dy.entity.Person;
import com.dy.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test02(){
        stringRedisTemplate.opsForValue().set("msg","hello");
        stringRedisTemplate.opsForValue().append("msg","world");
    }

    @Test
    public void test03(){
        redisTemplate.opsForValue().set("obj", new Cat("cat",12));
        Object obj = redisTemplate.opsForValue().get("obj");

        System.out.println(obj);
    }

    /**
     * 自定义的redisTemplate
     *  此处主要自定义了其序列化工具，将默认的jdk序列化替换为了json序列化工具
     *  此处反序列化会序列化为一个map，若向序列化为对应的对象则定制化序列化器即可
     */
    @Qualifier("myRedisTemplate")
    @Autowired
    private RedisTemplate<String,Object> myRedisTemplate;

    @Test
    public void test04(){
        myRedisTemplate.opsForValue().set("test", new Cat("haha",111));

        Object test = myRedisTemplate.opsForValue().get("test");

        System.out.println(test);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Cat implements Serializable{
        private String name;
        private Integer age;
    }

}
