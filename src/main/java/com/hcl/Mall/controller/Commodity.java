package com.hcl.Mall.controller;


import com.hcl.Mall.dao.Product;
import com.hcl.Mall.query.ProductMessage;
import com.hcl.Mall.service.*;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.utls.MallConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class Commodity {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    /**
     * 其中 bought 2表示所有的商品均要显示 1表示显示未购买的商品 0表示错误访问
     * index表示页数
     * @param bought
     * @return
     */
    @GetMapping("/type/{usertype}")
    @ResponseBody
    public JsonResult getTypeProduct(@RequestParam("bought") int bought, @RequestParam("index") int index,
                                     @PathVariable("usertype") String usertype){
        if(bought == 0 || usertype.equals("seller")){
            Pageable pageable = PageRequest.of(index,12);
            List<ProductMessage> productMessageList = this.productRepositoryService.findSomeProduct(pageable);
            return new JsonResult(1,usertype,productMessageList);
        }else if(bought == 1){
            Pageable pageable = PageRequest.of(index,12);
            List<ProductMessage> productMessageList = this.productRepositoryService.findSomeProductNotBought(pageable);
            return new JsonResult(2,usertype,productMessageList);
        }
        return new JsonResult(0,"错误访问",null);
    }

    /**
     * 存入当个商品的id到Session中，前后端分离
     * @param id
     * @return 返回单个商品的主页
     */

    @GetMapping("/index")
    public String setProduct(@RequestParam("id") long id,HttpSession session){
        session.setAttribute(MallConfig.PRODUCT_SESSION_KEY,id);
        return "Product";

    }

    /**
     * 返回单个商品的主页信息
     * @param session
     * @return 1 表示商品已购买，0 表示未购买
     */
    @GetMapping("/content")
    @ResponseBody
    public JsonResult getProduct(HttpSession session){
        long id = (long)session.getAttribute(MallConfig.PRODUCT_SESSION_KEY);
        Product product = this.productRepositoryService.getProductById(id);
        if(product.getSold() >=0)
            return new JsonResult(1,"成功获取商品信息",product);
        return new JsonResult(0,"商品已购买",product);
    }

    /**
     * 返回商品编辑页面
     * @return
     */
    @GetMapping("/editor")
    public String editor(@RequestParam("id") long id,HttpSession session){
        session.setAttribute(MallConfig.PRODUCT_SESSION_KEY,id);
        return "Publish";
    }

    @GetMapping("/delete")
    @ResponseBody
    public JsonResult deleteproduct(@RequestParam("id") long id){
//        this.productRepositoryService.delete(id);
        return new JsonResult(1,"成功删除该商品",null);
    }
}
