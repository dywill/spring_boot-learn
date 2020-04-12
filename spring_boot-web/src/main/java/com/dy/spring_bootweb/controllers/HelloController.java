package com.dy.spring_bootweb.controllers;

import com.dy.autoconfigure.HelloService;
import com.dy.spring_bootweb.ex.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayGello(){
        return "hello spring boot web";
    }

    @RequestMapping("/success")
    public String testThymeleaf(){
        return "success";
    }

    @ResponseBody
    @RequestMapping("/ex")
    public String exTest(){

        if(1 == 1){
            throw new MyException();
        }

        return "Ex";
    }

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/starter")
    public String testStarter(){
        return helloService.sayHello();
    }

    @RequestMapping("/ttt")
    public String forawadTest(){
        return "forward:/hello";
    }
}
