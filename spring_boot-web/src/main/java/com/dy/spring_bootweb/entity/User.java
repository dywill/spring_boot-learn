package com.dy.spring_bootweb.entity;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    public interface BaseView {};
    public interface DetailView extends BaseView{};

    @JsonView(BaseView.class)
    private String id;

    @JsonView(BaseView.class)
    private String username;

    @JsonView(DetailView.class)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
