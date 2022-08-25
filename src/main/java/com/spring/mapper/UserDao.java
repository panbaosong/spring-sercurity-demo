package com.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {

}
