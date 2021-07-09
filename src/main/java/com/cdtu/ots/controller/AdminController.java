package com.cdtu.ots.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdtu.ots.entity.AdminUser;
import com.cdtu.ots.entity.AdminCategory;
import com.cdtu.ots.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    AdminUserMapper userMapper;


    @RequestMapping("/admin")
    public String admin(){
        return "/admin/a";
    }

    @RequestMapping("/a")
    public String a(){
        return "/admin/a";
    }

    @RequestMapping("/category")
    public String category(){
        return "/admin/category";
    }

    @PostMapping("/getUserData")
    @ResponseBody
    public String getTableData(){
        ArrayList<AdminUser> userArrayList;
        userArrayList = userMapper.findById();
        return JSON.toJSONString(userArrayList);
    }

    @PostMapping("/getAdminCategory")
    @ResponseBody
    public String getAdminCategory(){
        ArrayList<AdminCategory> categoryArrayList;
        categoryArrayList = userMapper.findCategoryById();
        return JSON.toJSONString(categoryArrayList);
    }

    @PostMapping("/handleDelete")
    @ResponseBody
    public int deleteUser(int uId){
        int delete = userMapper.deleteUserBygID(uId);
        return delete;
    }

    @PostMapping("/categoryDelete")
    @ResponseBody
    public int deleteCategory(int cId){
        int delete = userMapper.deleteCategoryBygID(cId);
        return delete;
    }

    @PostMapping("/updateUserData")
    @ResponseBody
    public int updateUserData(String user){
        System.out.println(user);
        JSONObject user_Obj = JSONObject.parseObject(user);
        AdminUser _user = new AdminUser(user_Obj.getInteger("uId"), user_Obj.getString("userName"),
                user_Obj.getString("password"), user_Obj.getString("uPhone"),
                user_Obj.getString("uEmail"), user_Obj.getString("uLevel"),
                user_Obj.getString("uImage"));
        int error = 0;
        try {
            error = userMapper.updateUserDataById(_user);
        }catch (Exception e){
            e.printStackTrace();
        }

        return error;
    }

    @PostMapping("/updateCategoryData")
    @ResponseBody
    public int updateCategoryData(String category){
        System.out.println(category);
        JSONObject category_Obj = JSONObject.parseObject(category);
        AdminCategory _category = new AdminCategory(category_Obj.getInteger("cId"), category_Obj.getString("cName"),
                category_Obj.getString("cState"), category_Obj.getInteger("belong"));
        int error = 0;
        try {
            error = userMapper.updateCategoryDataById(_category);
        }catch (Exception e){
            e.printStackTrace();
        }

        return error;
    }




    @PostMapping("/addUserData")
    @ResponseBody
    public int addUserData(String user){
        int error = 0;
        System.out.println(user);
        JSONObject user_Obj = JSONObject.parseObject(user);
        AdminUser _user = new AdminUser(user_Obj.getInteger("uId"), user_Obj.getString("userName"),
                user_Obj.getString("password"), user_Obj.getString("uPhone"),
                user_Obj.getString("uEmail"), user_Obj.getString("uLevel"),
                user_Obj.getString("uImage"));

        try {
            error = userMapper.addUserData(_user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return error;
    }


    @PostMapping("/addCategoryData")
    @ResponseBody
    public int addCategoryData(String category){
        int error = 0;
        System.out.println(category);
        JSONObject category_Obj = JSONObject.parseObject(category);
        AdminCategory _category = new AdminCategory(category_Obj.getInteger("cId"), category_Obj.getString("cName"),
                category_Obj.getString("cState"), category_Obj.getInteger("belong"));

        try {
            error = userMapper.addCategoryData(_category);
        }catch (Exception e){
            e.printStackTrace();
        }
        return error;
    }



    @PostMapping("/getUserDataSize")
    @ResponseBody
    public int getUserDataSize() {
        return userMapper.findSize();
    }

    @PostMapping("/getCategoryDataSize")
    @ResponseBody
    public int getCategoryDataSize() {
        return userMapper.findCategorySize();
    }


    @PostMapping("/getUserPage")
    @ResponseBody
    public String getUserPage(int dataPage, int pageSize) {
        int page = (dataPage - 1) * pageSize;
        ArrayList<Map<String, Object>> userArrayList;
        userArrayList = userMapper.findAllSize(page, pageSize);
        System.out.println("dataPage");
        return JSON.toJSONString(userArrayList);
    }

    @PostMapping("/getCategoryPage")
    @ResponseBody
    public String getCategoryPage(int dataPage, int pageSize) {
        int page = (dataPage - 1) * pageSize;
        ArrayList<Map<String, Object>> categoryArrayList;
        categoryArrayList = userMapper.findAllCategorySize(page, pageSize);
        System.out.println("dataPage");
        return JSON.toJSONString(categoryArrayList);
    }





}
