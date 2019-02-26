package com.hcl.Mall.controller;

import com.hcl.Mall.dao.Buyer;
import com.hcl.Mall.dao.Seller;
import com.hcl.Mall.dom.SellerTitle;
import com.hcl.Mall.dom.BuyerTitle;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.utls.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/home")
public class Home {

    @GetMapping("")
    public String home(){
        return "Home";
    }

    /**
     * 主页上顶栏的状态信息
     *  seller 右侧为退出和发布，左侧 为用户昵称
     *  buyer 右侧为退出、财务和购物车 左侧为用户昵称
     *  非用户 为左侧为登录 右侧首页健
     *
     *  返回代码 1 表示用户
     *  返回代码2 表示卖者
     *  返回代码 0 表示没有用户登录
     * @param httpSession
     * @return
     */
    @GetMapping("/title")
    @ResponseBody
    public JsonResult title(HttpSession httpSession){

        Object result = StrUtil.getBuyerOrSeller(httpSession);

        log.info("登陆者登录"+result);

        if(result instanceof Buyer){
            Buyer buyer = (Buyer) result;
            BuyerTitle buyerTitle =
                    new BuyerTitle(buyer.getNickname(),"退出","财务","购物车");
            return new JsonResult(1,"买者",buyerTitle);
        }else if (result instanceof Seller){
            Seller seller = (Seller) result;
            SellerTitle sellerTitle =
                    new SellerTitle(seller.getNickname(),"退出","发布");
            return new JsonResult(2,"卖者",sellerTitle);
        }else{
            return new JsonResult(0,"无登录者",null);
        }

    }


}
