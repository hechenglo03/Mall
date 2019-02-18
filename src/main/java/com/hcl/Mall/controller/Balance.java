package com.hcl.Mall.controller;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.dao.User;
import com.hcl.Mall.dom.AccountProduct;
import com.hcl.Mall.query.ProductMessage;
import com.hcl.Mall.service.ProductRepositoryService;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.utls.MallConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Controller
@RequestMapping("/balance")
public class Balance{


    @Autowired
    private ProductRepositoryService productRepositoryService;

    /**
     * 购物车添加商品
     * @param id
     * @param sum
     * @param session
     * @return
     */
    @GetMapping("/add")
    @ResponseBody
    public JsonResult addProduct(long id, int sum){

        this.productRepositoryService.updatesum(sum,id);

        return new JsonResult(1,"成功添加购物车",null);

    }

    @GetMapping("/account")
    @ResponseBody
    public JsonResult account(List<AccountProduct> accountProductList){

        for(AccountProduct accountProduct:accountProductList){
            this.productRepositoryService.bought(accountProduct.getId());
        }
        return new JsonResult(1,"成功购买",null);
    }

    @GetMapping("/products")
    @ResponseBody
    public JsonResult getBoughtProducts(@RequestParam("index") int index){
        Pageable pageable = PageRequest.of(index,12);
        List<ProductMessage> list = this.productRepositoryService.findSomeProductBought(pageable);
        return new JsonResult(1,"成功显示",list);
    }
}
