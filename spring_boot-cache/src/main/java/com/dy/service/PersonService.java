package com.dy.service;

import com.dy.entity.Person;
import com.dy.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


/**
 * 可以用于指定该类下，默认的
 */
@CacheConfig(cacheManager = "myCacheManager")
@Service
public class PersonService {

    public PersonService() {
    }

    @Autowired
    private PersonMapper personMapper;

    /**
     * cacheNames/value         指定缓存组件（即Cache）的名字
     * key                      指定缓存数据时使用的key，默认为（k-v）：（方法的参数：方法返回值）
     *                              缓存键的编写可以使用spel： #id：参数id的值 #a0 #p0 #root.args[0]
     * keyGenerator             key的生成器，可以自定义键的生成策略（故和key这个属性二选一）
     * cacheManager             指定缓存管理器，或者cacheResolver指定获取解析器
     * condition                在满足该条件的情况下才进行缓存
     * unless                   否定缓存，在满足该条件情况下，不进行缓存
     * sync                     是否使用缓存的异步模式
     */
    // 在方法上开启缓存
    @Cacheable(cacheNames = "person",cacheManager = "myCacheManager",key = "#id"/*keyGenerator = "uuidKeyGenerator"*/)
    public Person getById(Integer id){
        System.out.println("--- getById "+ id +" ---");
        return personMapper.selectById(id);
    }

    /**
     * 注解中属性和@Cacheable相同
     *  其流程为先运行目标方法，然后将方法返回值存入缓存
     */
    @CachePut(cacheNames = "person",key = "#result.id")
    public Person updateById(Person person){
        Assert.notNull(person,"更新对象不能为空");
        Assert.notNull(person.getId(),"跟新对象主键不能为空");
        System.out.println("--- updateById "+ person +" ---");
        int i = personMapper.updateById(person);
        return person;
    }

    /**
     * 用于清除缓存中的数据
     *  此处为删除缓存key为参数id的缓存
     */
    @CacheEvict(value = "person", key = "#p0")
    public void deleteById(Integer id){
        System.out.println("cache "+ id +" clear");
    }

    /**
     * 清除缓存的注解属性同样和@Cacheable相同
     *  多了两个属性
     *  allEntries          默认false     指定清除该缓存的所有数据
     *  beforeInvocation    默认false     指定清除缓存是否会在方法前调用，默认为在方法调用结束后清除
     */
    @CacheEvict(value = "person", allEntries = true, beforeInvocation = true)
    public void deleteAll(){
        System.out.println(" ======> cache all clear ing...");
    }
}
