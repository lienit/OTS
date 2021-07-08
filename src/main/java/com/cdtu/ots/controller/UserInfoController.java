package com.cdtu.ots.Controller;

import com.cdtu.ots.entity.User;
import com.cdtu.ots.service.UserService;
import com.cdtu.ots.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserInfoController {
   @Autowired
   private UserService userService;

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


}
