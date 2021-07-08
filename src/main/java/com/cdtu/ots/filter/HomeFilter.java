package com.cdtu.ots.filter;

import com.alibaba.fastjson.JSON;
import com.cdtu.ots.entity.User;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName HomeFilter
 * @Description TODO
 * @Author mars_sea
 * @Date 2021/7/1
 **/
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/businessman/*", filterName = "businessmanFilter")
public class HomeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        request.getSession(false);
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getuLevel() == "3"){
            // 放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            System.out.println("嘿嘿");
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
