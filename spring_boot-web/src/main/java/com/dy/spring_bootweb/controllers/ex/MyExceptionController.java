package com.dy.spring_bootweb.controllers.ex;

import com.dy.spring_bootweb.ex.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
public class MyExceptionController {

    @ExceptionHandler(MyException.class)
    public Map handleException(Exception ex){

        Map<String, Object> map = new HashMap<>();
        map.put("code",1001);
        map.put("ex",ex.getMessage());
        return map;
    }

}
