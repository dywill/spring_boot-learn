package com.dy.springboot_data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@MapperScan({"com.dy.springboot_data.mapper"})
@SpringBootApplication
public class SpringBootDataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJdbcApplication.class,args);
    }
}
