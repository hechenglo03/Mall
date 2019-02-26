package com.hcl.Mall.service.imp;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.dao.Seller;
import com.hcl.Mall.repository.ProductRepository;
import com.hcl.Mall.service.ProductRepositoryService;
import com.hcl.Mall.service.*;
import com.hcl.Mall.utls.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.net.URL;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryServicelmplTest {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @Autowired
    private BuyerRepositoryService userRepositoryService;

    @Autowired
    private ProductRepository productRepository;



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

    @Test
    public void Test2(){
        Product product = new Product();
        product.setId(1);
        product.setSummary("LONE");
        product.setContent("Lyi");
        product.setPrice(9.99);
        product.setPic("http://cms-bucket.ws.126.net/2019/02/25/b1344c60c79745baa800a96a2586c1fc.jpg");
        this.productRepository.save(product);
    }
}