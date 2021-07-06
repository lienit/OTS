package com.cdtu.ots.util;


import com.cdtu.ots.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class MailUtil {
    //    /**
//     * 发送邮件
//     *
//     * @param to   给谁发
//     * @param text 发送内容
//     **/
    @Autowired
    private static MailService mailService;

    public static String CheckCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;

        try {
            mailService.sendMail(email,"主题：这是个神奇的验证码",message);
        }catch (Exception e){
            return "";
        }
        return checkCode;
    }
}
