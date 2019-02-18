package com.hcl.Mall.service.imp;

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

    @Test
    public void ProductTest() throws URISyntaxException {
        URL url = FileUtil.class.getResource("/static/img");
        System.out.println(url.getPath().substring(1));


    }

}