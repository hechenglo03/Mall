package com.hcl.Mall.controller;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.repository.ProductRepository;
import com.hcl.Mall.service.ProductRepositoryService;
import com.hcl.Mall.service.TestDaoRepositoryService;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestDaoRepositoryService testDaoRepositoryService;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("")
      public String test(){
        return "test";
    }

    @RequestMapping("/attack")
    @ResponseBody
    public Product dir(HttpServletRequest request){
        return this.productRepository.getById(1);

    }
}
