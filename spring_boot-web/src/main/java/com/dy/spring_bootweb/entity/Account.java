package com.dy.spring_bootweb.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Account {

    @ApiModelProperty("用户姓名")
    private String accountName;

    private Integer gender;

    private Date birthday;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
