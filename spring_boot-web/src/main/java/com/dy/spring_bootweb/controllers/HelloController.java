package com.dy.spring_bootweb.controllers;

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

}
