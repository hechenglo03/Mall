package com.hcl.Mall.controller;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class Test {



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
