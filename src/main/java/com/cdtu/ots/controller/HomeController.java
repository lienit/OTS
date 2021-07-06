package com.cdtu.ots.Controller;

import com.cdtu.ots.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class HomeController {
    @Autowired
    private MailService mailService;


    @RequestMapping("/home")
    public String Home(){
        return "home";
    }

    @RequestMapping("/signin")
    public String SignIn(){
        return "signIn";
    }

    @PostMapping("getCheckCode")
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

}
