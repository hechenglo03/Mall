package com.hcl.Mall.controller;

import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.dao.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping("")
    public String test(){
        return "publish";
    }

    @RequestMapping("/attack")
    @ResponseBody
    public String dir(HttpServletRequest request){

        System.out.println(request.getServletPath());
        return "OK";
    }
}
