package com.dy.spring_bootweb.controllers;

import com.dy.spring_bootweb.entity.Account;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class SwaggerController {

    @GetMapping("/{id}")
    @ApiOperation(value = "用户查询服务")
    public Account getById(@ApiParam("用户id") @PathVariable Integer id){

        Account account = new Account();

        account.setAccountName("jack");
        account.setGender(1);
        account.setBirthday(new Date());

        return account;
    }

    @PutMapping
    @ApiOperation(value = "用户更新服务")
    public String update(@RequestBody Account account){
        return "success";
    }


}
