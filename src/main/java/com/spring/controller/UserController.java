package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("login")
    public String login(){
        return "登录成功！";
    }
    @RequestMapping("name")
    public String name(){
        return "潘保松！";
    }

}
