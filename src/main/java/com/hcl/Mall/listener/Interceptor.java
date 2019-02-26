package com.hcl.Mall.listener;


import com.hcl.Mall.utls.MallConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class Interceptor implements HandlerInterceptor{

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute(MallConfig.USER_SESSION_KEY) != null)
            return  true;
        response.sendRedirect("/login");//直接返回登录页面
        return false;
    }
}
