package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.AdminUser;
import com.cdtu.ots.entity.AdminCategory;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface AdminUserMapper {


    @Select("select * from user")
    public ArrayList<AdminUser> findById();

    @Select("select * from category")
    public ArrayList<AdminCategory> findCategoryById();


    @Delete("delete from user where uId = #{uId}")
    public int deleteUserBygID(@Param("uId") int uId);

    @Delete("delete from category where cId = #{cId}")
    public int deleteCategoryBygID(@Param("cId") int cId);


    @Update("UPDATE user SET uId = #{user.uId}, userName = #{user.userName}, password = #{user.password}, " +
            "uPhone = #{user.uPhone},uEmail = #{user.uEmail}, uLevel = #{user.uLevel}, uImage = #{user.uImage}" +
            "where uId = #{user.uId}")
    public int updateUserDataById(@Param("user") AdminUser user);

    @Update("UPDATE category SET cId = #{category.cId}, cName = #{category.cName}, cState = #{category.cState}, " +
            "Belong = #{category.Belong}")
    public int updateCategoryDataById(@Param("category") AdminCategory category);

    @Select("select count(uId) from user")
    public int findSize();

    @Select("select count(cId) from category")
    public int findCategorySize();

    @Insert("INSERT INTO user (uId, userName, password, uPhone, uEmail, uLevel, uImage) " +
            "VALUES(#{user.uId}, #{user.userName}, #{user.password},#{user.uPhone}," +
            "#{user.uEmail}, #{user.uLevel}, #{user.uImage})")
    public int addUserData(@Param("user") AdminUser user);

    @Insert("INSERT INTO category (cId, cName, cState, Belong) " +
            "VALUES(#{category.cId}, #{category.cName}, #{category.cState},#{category.Belong})")
    public int addCategoryData(@Param("category") AdminCategory category);

    @Select("select * from user LIMIT #{page},#{size}")
    public ArrayList<Map<String, Object>> findAllSize(@Param("page") int page, @Param("size") int size);

    @Select("select * from category LIMIT #{page},#{size}")
    public ArrayList<Map<String, Object>> findAllCategorySize(@Param("page") int page, @Param("size") int size);
}
