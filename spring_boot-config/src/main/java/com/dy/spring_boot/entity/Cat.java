package com.dy.spring_boot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "cat")
@PropertySource(value = "classpath:cat.properties")
//@PropertySource(value = "classpath:yaml/cat.yml")
@Component
public class Cat {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
