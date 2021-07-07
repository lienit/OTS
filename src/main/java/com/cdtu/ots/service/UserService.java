package com.cdtu.ots.service;

import com.cdtu.ots.entity.User;
import com.cdtu.ots.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByUserName(String username){
       User user = userMapper.findByUserName(username);
       return user;
    }

    public Boolean insertUser(User user){
        Boolean aBoolean = userMapper.insertUser(user);
        return aBoolean;
    }
}
