package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.spring.mapper")
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true) //开启springsecurity注解
public class SpringsercuritydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsercuritydemoApplication.class, args);
    }

}
