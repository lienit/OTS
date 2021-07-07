package com.cdtu.ots.Controller;

import com.alibaba.fastjson.JSON;
import com.cdtu.ots.entity.User;
import com.cdtu.ots.service.MailService;
import com.cdtu.ots.service.UserService;
import com.cdtu.ots.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class CommonController {
    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;


    @RequestMapping("/home")
    public String Home(){
        return "home";
    }

    @RequestMapping("/iStatus")
    @ResponseBody
    public String iStatus(HttpServletRequest request){
        String message = "";
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            String s = JSON.toJSONString(user);
            return s;
        }
        return message;
    }

    @RequestMapping("/login")
    public String Login(){

        return "login";
    }

    @PostMapping("/isLogin")
    @ResponseBody
    public String isLogin(String name, String password, HttpServletRequest request){
        String message = "";

        String md5 = MD5Util.getMD5(password);
        User byLogin = userService.findByLogin(name, md5);
        if (byLogin != null){
            request.getSession().setAttribute("user",byLogin);
            if (byLogin.getuLevel().equals("1") || byLogin.getuLevel().equals("2")){
                message = "user";
            }else {
                message = "store";
            }
        }else{
            message = "error";
        }

        return message;

    }

    @PostMapping("/quit")
    @ResponseBody
    public String quit(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "success";
    }

    @RequestMapping("/signin")
    public String SignIn(){
        return "signIn";
    }

    @PostMapping("/getCheckCode")
    @ResponseBody
    public String getCheckCode(String mailbox){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        System.out.println(mailbox);
        try {
            mailService.sendMail(mailbox,"主题：验证邮箱",message);


        }catch (Exception e){
            return "";
        }
        return checkCode;
    }

    @PostMapping("/userSig")
    @ResponseBody
    public String sigIn(String veCode,long dateTime,String authCode){
        String message = "";

        long time1 = new Date().getTime();
        long num = time1 - dateTime;
        SimpleDateFormat ss = new SimpleDateFormat("ss");
        String format = ss.format(num);
        int i = Integer.parseInt(format);
        System.out.println(i);
        if (i < 60){
            if (veCode.equals(authCode)){
                message = "success";
            }else {
                message = "error";
            }
        }else {
            message = "overtime";
        }

        return message;
    }
    @PostMapping("/userSigin")
    @ResponseBody
    public String userSigin(String emila, String username, String password, String phone ){
        User byUserName = userService.findByUserName(username);
        String message = "";

        if (byUserName!=null  ){
            message = "error1";
        }else if(emila=="") {
            message = "error2";
        }
        else {
            User user = new User();
            String md5 = MD5Util.getMD5(password);
            user.setPassword(md5);
            user.setuEmail(emila);
            user.setuPhone(phone);
            user.setUserName(username);
            Boolean aBoolean = userService.insertUser(user);
            if (aBoolean){
                message = "success";
            }
        }
        return message;
    }

}
