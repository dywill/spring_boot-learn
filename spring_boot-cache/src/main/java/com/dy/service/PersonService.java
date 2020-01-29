package com.dy.service;

import com.dy.entity.Person;
import com.dy.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    // 在方法上开启缓存
    @Cacheable(cacheNames = "person")
    public Person getById(Integer id){
        System.out.println("--- getById "+ id +" ---");
        return personMapper.selectById(id);
    }
}
