package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where userName=#{username}")
    public User findByUserName(String Username);

    @Select("select * from user where userName = #{username} and password = #{password}")
    public User findByLogin(String username, String password);

    @Select("SELECT * from user where uLevel = #{uLevel}")
    public User findByLevel(String uLevel);



    @Insert("insert into user(userName,password,uPhone,uEmail) values(#{userName},#{password},#{uPhone},#{uEmail})")
    public Boolean insertUser(User user);

    @Update("update user set uEmail=#{email} where userName = #{username}")
    public boolean updateEmail(String username, String email);

    @Update("update user set password=#{password} where userName = #{username}")
    public Boolean updatePsw(String username, String password);

    @Update("update user set uLevel=#{uLevel} where userName = #{username}")
    public Boolean updateLevel(String username,String uLevel);

}
