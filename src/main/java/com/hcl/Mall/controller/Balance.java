package com.hcl.Mall.controller;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.dao.User;
import com.hcl.Mall.dom.AccountProduct;
import com.hcl.Mall.query.ProductBillMessage;
import com.hcl.Mall.query.ProductMessage;
import com.hcl.Mall.service.ProductRepositoryService;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.utls.MallConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/balance")
public class Balance{


    @Autowired
    private ProductRepositoryService productRepositoryService;


    /**
     *
     * 返回购物车页面
     * @return
     */
    @RequestMapping("/car")
    public String shoppingCar(){
        return "ShoppingCar";
    }

    /**
     * 获取购物车中商品
     * index 表示购物车翻页的索引
     * @return
     */
    @RequestMapping("/carlist")
    @ResponseBody
    public JsonResult getCarlist(@RequestParam("index") int index){
        Pageable pageable = PageRequest.of(index,12);
        List<ProductMessage> list = this.productRepositoryService.getCarList(pageable);
        return new JsonResult(1,"显示商品",list);
    }

    /**
     * 购物车添加商品
     * @param id
     * @param sum
     * @param session
     * @return 0 表示用户没有登录
     *          1 表示用户登录，成功加入购物车
     */
    @GetMapping("/add")
    @ResponseBody
    public JsonResult addProduct(long id, int sum,HttpSession session) throws Exception{

        if(session.getAttribute(MallConfig.USER_SESSION_KEY) == null){
            return new JsonResult(0,"用户还没有登录","/login");
        }

        this.productRepositoryService.updatesum(sum,id);

        return new JsonResult(1,"成功添加购物车",null);

    }

    /**
     *
     *
     *商品结算，成功的购买商品
     * @param accountProductList
     * @return
     */
    @PostMapping("/account")
    @ResponseBody
    public JsonResult account(@RequestBody ArrayList<AccountProduct> accountProductList){

        for(AccountProduct accountProduct:accountProductList){
            this.productRepositoryService.bought(accountProduct.getId());
        }
        return new JsonResult(1,"成功购买",null);
    }


    /**
     * 账单页面展示
     * @param index
     * @return
     */

    @GetMapping("/bill")
    public String getBill(){
        return "Bill";
    }

    /**
     * 展示账单中商品的结算条目
     * @param index
     * @return
     */
    @GetMapping("/products")
    @ResponseBody
    public JsonResult getBoughtProducts(@RequestParam("index") int index){
        Pageable pageable = PageRequest.of(index,12);
        List<ProductBillMessage> list = this.productRepositoryService.findSomeProductBought(pageable);
        return new JsonResult(1,"成功显示",list);
    }
}
