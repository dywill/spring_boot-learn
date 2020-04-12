package com.dy.spring_bootweb.controllers;

import com.dy.spring_bootweb.entity.User;
import com.dy.spring_bootweb.service.DeferredResultHolder;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 补充：
 *  MockMVC的用法
 *      简介：spring中用于进行接口测试的工具
 *      ~ 详细使用可以参照测试案例
 *
 *  JsonView用法：
 *      简介：能较为方便的自定义返回视图的字段
 *      ~ 详细使用参照 /user/detail 和 /user/{id:\d+} 两个接口， 其中 {id:\d+} 表示对路径参数id做了一次正则判断是否符合规则
 *
 *  SpringMVC进行异步处理
 *
 *      方法：
 *          1.返回Callable对象即可
 *          2.返回DeferredResult对象（该方法可以解耦，不必像callable一样，在controller方法中新建线程）
 *
 */
@RestController
@RequestMapping("/rest")
public class RestTestController {

    private static Logger logger = LoggerFactory.getLogger(RestTestController.class);

    @GetMapping
    public Map<String,Object> restTest(){

        Map<String, Object> map = Maps.newHashMap();
        map.put("id",1);
        map.put("name","jack");
        return map;
    }

    @PostMapping("/user/{id:\\d+}")
    @JsonView(User.BaseView.class)
    public User getById(@PathVariable String id, String username){

        User user = new User();

        user.setId(id);
        user.setUsername("jack");


        return user;
    }

    @PostMapping("/user/detail")
    @JsonView(User.DetailView.class)
    public User getDetailById(String username){

        User user = new User();

        user.setId("23");
        user.setUsername("jack");
        user.setPassword("123");

        return user;
    }

    @GetMapping("/async")
    public Callable<String> asyncTest(){

        logger.warn("main thread ------------ start");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                logger.warn("sub thread ------------ execute");
                return "hello async";
            }
        };
        logger.warn("main thread ------------ end");
        return callable;
    }

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/async2")
    public DeferredResult<String> asyncTest2(){
        String random = RandomStringUtils.random(8);

        DeferredResult<String> deferredResult = new DeferredResult<>();

        // 此处可视为 加入 消息队列 等待处理
        deferredResultHolder.put(random, deferredResult);

        return deferredResult;
    }


}
