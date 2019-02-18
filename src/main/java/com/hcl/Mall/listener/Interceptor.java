package com.hcl.Mall.listener;


import com.hcl.Mall.utls.MallConfig;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Interceptor implements HandlerInterceptor{

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();//获取访问的路径
        if(path.indexOf("balance") != -1){//直接访问结算页面，如果没有登录直接返回登录页面
            if(request.getSession().getAttribute(MallConfig.USER_SESSION_KEY) == null){
                response.sendRedirect("/login");
                return false;
            }

        }

        if(path.indexOf("editor") != -1){
            if(request.getSession().getAttribute(MallConfig.SELLER_SESSION_KEY) == null){
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }
}
