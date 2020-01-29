package com.dy.controllers;

import com.dy.entity.Person;
import com.dy.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SuppressWarnings("ALL")
@RestController
public class TestController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person/{id}")
    public Person person(@PathVariable("id") Integer id){
        log.info("--- controller start ---");
        return personService.getById(id);
    }


}
