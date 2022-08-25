package com.spring.config;

import com.spring.entity.PasswordEncoderImpl;
import com.spring.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoderImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置403没有权限的跳转界面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.formLogin() //自定义编写自己的登录界面
        .loginPage("/login.html") //登录界面设置
        .loginProcessingUrl("/user/login") //设置哪个是登录的 url。
        .defaultSuccessUrl("/success.html").permitAll(); //登录成功之后跳转的路径

        http.authorizeRequests() //定义哪些http方法可以自由访问 哪些需要登录
        .antMatchers("/","/test/hello/","/user/login/").permitAll() //设置哪些路径可以直接访问 不需要认证
        //hasAuthority 如果当前的主体具有指定的权限，则返回 true,否则返回 false
        //.antMatchers("/test/index").hasAuthority("admins")

        //如果当前的主体有任何提供的角色（给定的作为一个逗号分隔的字符串列表）的话，返回true
        //.antMatchers("/test/index").hasAnyAuthority("admins","manager")

        //如果当前主体有设定的角色 就可以访问 ROLE_archive
        //.antMatchers("/test/index").hasRole("archive")

        //如果当前主题有任一角色返回true 就可以访问
        .antMatchers("/test/index").hasAnyRole("archive","dangan")

        .anyRequest().authenticated() //剩下的所有都需要访问
        .and().csrf().disable();

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").permitAll();
    }
}
