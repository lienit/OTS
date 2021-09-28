package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("select * from user where userName=#{username}")
    public User findByUserName(String Username);

    @Select("select * from user where userName = #{username} and password = #{password}")
    public User findByLogin(String username, String password);

    @Select("SELECT * from user where uLevel = #{uLevel}")
    public User findByLevel(String uLevel);

    @Select("select * from user")
    public List<User> findAll();

    /**
     * 查询待审核用户
     * @param page , size
     * @param page
     * @param size
     * @return
     */
    @Select("select * from user where uLevel=2 limit #{page},#{size}")
    public ArrayList<Map<String, Object>> findByAuditUser(int page, int size);

    /**
     * 统计待审核用户数量
     * @return
     */
    @Select("select count(uId) from user where uLevel=2")
    public String auditUserSize();

    @Insert("insert into user(userName,password,uPhone,uEmail) values(#{userName},#{password},#{uPhone},#{uEmail})")
    public Boolean insertUser(User user);

    @Update("update user set uEmail=#{email} where userName = #{username}")
    public boolean updateEmail(String username, String email);

    @Update("update user set password=#{password} where userName = #{username}")
    public Boolean updatePsw(String username, String password);

    @Update("update user set uLevel=#{uLevel} where userName = #{username}")
    public Boolean updateLevel(String username,String uLevel);



}
