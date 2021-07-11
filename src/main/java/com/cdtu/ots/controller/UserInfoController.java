package com.cdtu.ots.controller;

import com.alibaba.fastjson.JSON;
import com.cdtu.ots.entity.Address;
import com.cdtu.ots.entity.User;
import com.cdtu.ots.service.AddressService;
import com.cdtu.ots.service.UserService;
import com.cdtu.ots.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Controller
public class UserInfoController {
   @Autowired
   private UserService userService;

   @Autowired
   private AddressService addressService;

    @RequestMapping("/userInfo")
    public String userInfo(){
        return "/user/userInfo";
    }

    @RequestMapping("/editEmail")
    public String  editEmail(){

        return "/user/emailEdit";
    }


    @PostMapping("/openEmail")
    @ResponseBody
    public String openEmail(String email,String veCode,long dateTime,String authCode, HttpServletRequest request){
        System.out.println(email);
        String message = "";
        long time1 = new Date().getTime();
        long num = time1 - dateTime;
        SimpleDateFormat ss = new SimpleDateFormat("ss");
        String format = ss.format(num);
        int i = Integer.parseInt(format);
        System.out.println(i);
        if (i < 60) {
            if (veCode.equals(authCode)) {
                User user = (User) request.getSession().getAttribute("user");
                userService.updateEmail(user.getUserName(), email);
                message = "success";
            }else {
                message = "error";
            }
        }
        User user = (User) request.getSession().getAttribute("user");
        user.setuEmail(email);
        request.getSession().setAttribute("user",user);
        return message;
    }

    @RequestMapping("/pswEdit")
    public String pswEdit(){
        return "/user/pswEdit";
    }

    @PostMapping("/openPsw")
    @ResponseBody
    public String openPsw(String password1, String password2, HttpServletRequest request){
        String message = "";
        User user = (User)request.getSession().getAttribute("user");
        String md5 = MD5Util.getMD5(password1);
        userService.updatePsw(user.getUserName(),md5 );
        request.getSession().removeAttribute("user");



        return message;
    }

    @RequestMapping("/addressInfo")
    public String addressInfo(){
        return "/user/AddressInfo";
    }

    @RequestMapping("/findAddress")
    @ResponseBody
    public String findAddress(HttpServletRequest request){
        String message = "";

        User user =(User) request.getSession().getAttribute("user");

        ArrayList<Map<String, Object>> all = addressService.findAll(user.getuId());
        String s = JSON.toJSONString(all);

        return s;
    }

    @PostMapping("/insertAddress")
    @ResponseBody
    public String insertAddress(int coutAdd,String name, String address, String phone, String aPostcode,boolean isDefault, HttpServletRequest request){
        String message = "";
        User user = (User)request.getSession().getAttribute("user");

        if (isDefault){
            Address aDefault = addressService.findDefault(isDefault,user.getuId());
           if (aDefault!=null){
               aDefault.setDefault(false);
               addressService.updateDefault(aDefault.getaId(),aDefault.getDefault());
           }
        }
        System.out.println(coutAdd);
        if(coutAdd<=4){
            Address address1 = new Address();
            address1.setaAddress(address);
            address1.setaConsignee(name);
            address1.setaPhone(phone);
            address1.setDefault(isDefault);
            address1.setaPostcode(aPostcode);
            address1.setaUserid(user.getuId());
            addressService.insertAddress(address1);
            message = "success";
        }else{
            message = "error";
        }

        return message;
    }

    @PostMapping("/editAddress")
    @ResponseBody
    public String editAddress(int aId,String name, String address, String phone, String aPostcode,boolean isDefault, HttpServletRequest request){
        String message = "";

        User user = (User)request.getSession().getAttribute("user");

        if (isDefault){
            Address aDefault = addressService.findDefault(isDefault,user.getuId());
            if (aDefault!=null){
                aDefault.setDefault(false);
                addressService.updateDefault(aDefault.getaId(),aDefault.getDefault());
            }
        }
        Address address1 = new Address();
        address1.setaId(aId);
        address1.setaAddress(address);
        address1.setaConsignee(name);
        address1.setaPhone(phone);
        address1.setDefault(isDefault);
        address1.setaPostcode(aPostcode);
        address1.setaUserid(user.getuId());
        addressService.updateAddress(address1);
        message = "success";
        return message;
    }

    @PostMapping("/deleteAddress")
    @ResponseBody
    public String deleteAddress(HttpServletRequest request, int aId){
        String message = "";

        System.out.println(aId);
        Boolean aBoolean = addressService.deleteAddress(aId);
        if (aBoolean){
            message = "success";
        }
        return message;
    }

    @RequestMapping("/storeIn")
    public  String storeIn(){
        return "/user/storeIn";
    }

    @PostMapping("/userApp")
    @ResponseBody
    public String userApp(String username){
        String message = "";
        Boolean aBoolean = userService.updateLevel(username, "2");

        if (aBoolean){
            message = "success";
        }
        return message;
    }

}
