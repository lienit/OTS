package com.cdtu.ots.service;

import com.cdtu.ots.entity.User;
import com.cdtu.ots.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByUserName(String username){
       User user = userMapper.findByUserName(username);
       return user;
    }

    public User findByLogin(String username, String password){
        User byLogin = userMapper.findByLogin(username, password);
        return byLogin;
    }

    public User findByLevel(String uLevel){
        User byLevel = userMapper.findByLevel(uLevel);
        return byLevel;
    }
    public List<User> findAll(){
        List<User> all = userMapper.findAll();
        return all;
    }

    public Boolean insertUser(User user){
        Boolean aBoolean = userMapper.insertUser(user);
        return aBoolean;
    }

    public boolean updateEmail(String username, String email){
        boolean b = userMapper.updateEmail(username, email);
        return  b;
    }
    public Boolean updatePsw(String username, String password){
        Boolean aBoolean = userMapper.updatePsw(username, password);
        return aBoolean;
    }
    public Boolean updateLevel(String username,String uLevel){
        Boolean aBoolean = userMapper.updateLevel(username, uLevel);
        return  aBoolean;
    }
}
