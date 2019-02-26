package com.hcl.Mall.controller;

import com.hcl.Mall.dao.Buyer;
import com.hcl.Mall.dao.Seller;
import com.hcl.Mall.service.*;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.utls.MallConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class Login {

    @Autowired
    private BuyerRepositoryService buyerRepositoryService;

    @Autowired
    private SellerRepositoryService sellerRepositoryService;

    /**
     * 返回登陆页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/{usertype}/getlogin")
    @ResponseBody
    public JsonResult getLogin(@RequestParam("username") String username,
                               @RequestParam("passwd") String passwd,
                               @PathVariable String usertype,
                               HttpSession httpSession) {

        if (usertype.equals("buyer")) {
            Buyer buyer = this.buyerRepositoryService.getBuyer(username, passwd);
            if (buyer == null) {
                log.info("买者登录失败");
                return new JsonResult(0, "密码错误", null);
            } else {
                log.info("买者登录成功");
                httpSession.setAttribute(MallConfig.USER_SESSION_KEY, buyer);
                return new JsonResult(1, "/home", null);
            }
        } else {
            Seller seller = this.sellerRepositoryService.getSeller(username, passwd);
            if (seller == null) {
                log.info("卖者登录失败");
                return new JsonResult(0, "密码错误", null);
            }
            else {
                log.info("卖者登录成功");
                httpSession.setAttribute(MallConfig.USER_SESSION_KEY,seller);
                return new JsonResult(1, "/home", null);
            }
        }

    }

    /**
     * 账号退出操作
     *
     * @param session
     * @return
     */
    @GetMapping("/{usertype}/loginout")
    public String getLoginOut(HttpSession session) {
        if(session.getAttribute(MallConfig.USER_SESSION_KEY) != null)
            return "login";
        else
            return null;

    }
}
