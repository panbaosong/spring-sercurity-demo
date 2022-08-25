package com.spring.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("index")
    public String index(){
        return "my index";
    }

    @RequestMapping("update")
    @Secured({"ROLE_archive","ROLE_amdins"})
    //@Secured 判断是否具有角色，另外需要注意的是这里匹配的字符串需要添加前缀“ROLE_“。
    public String update(){
        return "my update";
    }

    @RequestMapping("add")
    @PreAuthorize("hasAnyAuthority('admins')")
    //@PreAuthorize：注解适合进入方法前的权限验证
    public String add(){
        return "my add";
    }

    @RequestMapping("delete")
    @PostAuthorize("hasAnyAuthority('admin')")
    //@PreAuthorize：在方法执行后再进行权限验证
    public String delete(){
        System.out.println("delete。。。。。");
        return "my add";
    }


}
