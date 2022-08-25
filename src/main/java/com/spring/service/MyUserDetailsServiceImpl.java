package com.spring.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.entity.PasswordEncoderImpl;
import com.spring.entity.User;
import com.spring.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        User user = userDao.selectOne(wrapper);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在！");
        }else{
            List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,dept,ROLE_archive");
            return new org.springframework.security.core.userdetails.User(user.getUsername(),new PasswordEncoderImpl()
                    .encode(user.getPassword()),auths);
        }
    }
}
