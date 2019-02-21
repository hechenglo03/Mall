package com.hcl.Mall.service.imp;

import com.hcl.Mall.controller.TestDao;
import com.hcl.Mall.dao.Product;
import com.hcl.Mall.dao.Seller;
import com.hcl.Mall.query.ProductMessage;
import com.hcl.Mall.service.ProductRepositoryService;
import com.hcl.Mall.service.*;
import com.hcl.Mall.utls.FileUtil;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.data.domain.Pageable;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryServicelmplTest {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private TestDaoRepositoryService testDaoRepositoryService;

    @Autowired
    private SellerRepositoryService sellerRepositoryService;

    @Test
    public void ProductTest() throws URISyntaxException {
        URL url = FileUtil.class.getResource("/static/img");
        System.out.println(url.getPath().substring(1));


    }


    @Test
    public void Test(){
        ProductRepositoryService productRepositoryService = new ProductRepositoryServicelmpl();
        Product product = new Product();
        product.setContent("one");
        product.setSummary("one");
        product.setId(1);
        productRepositoryService.save(product);
    }

    @Test
    public void Test1(){
        Seller seller = this.sellerRepositoryService.getSeller("hechenglo03","e10adc3949ba59abbe56e057f20f883e");
        System.out.println(seller);
    }
}