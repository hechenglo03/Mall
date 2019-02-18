package com.hcl.Mall.controller;

import com.hcl.Mall.dao.Seller;
import com.hcl.Mall.dao.User;
import com.hcl.Mall.service.*;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.utls.MallConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private SellerRepositoryService sellerRepositoryService;

    /**
     * 返回登陆页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/{usertype}/getlogin")
    @ResponseBody
    public JsonResult getLogin(@RequestParam("username") String username,
                               @RequestParam("passwd") String passwd,
                               @PathVariable String usertype,
                               HttpSession httpSession)
    {

        if(usertype.equals("user")){
            User user = this.userRepositoryService.getUser(username,passwd);
            if(user == null){
                return new JsonResult(0,"密码错误",null);
            }else{
                httpSession.setAttribute(MallConfig.USER_SESSION_KEY,user);
                return new JsonResult(1,"/home",null);
            }
        }else{
            Seller seller = this.sellerRepositoryService.getSeller(username,passwd);
            if(seller == null)
                return new JsonResult(0,"密码错误",null);
            else {
                httpSession.setAttribute(MallConfig.SELLER_SESSION_KEY,seller);
                return new JsonResult(1, "/home", null);
            }
        }

    }

    /**
     * 账号退出操作
     * @param usertype
     * @param session
     * @return
     */
    @GetMapping("/{usertype}/loginout")
    public JsonResult getLoginOut(@PathVariable String usertype,HttpSession session){
        if(usertype.equals("user")){
            if(session.getAttribute(MallConfig.USER_SESSION_KEY) != null){
                session.removeAttribute(MallConfig.USER_SESSION_KEY);
                return new JsonResult(1,"成功退出",null);
            }else{
                return new JsonResult(0,"错误访问",null);
            }
        }else if(usertype.equals("seller")){
            if(session.getAttribute(MallConfig.SELLER_SESSION_KEY) != null){
                session.removeAttribute(MallConfig.SELLER_SESSION_KEY);
                return new JsonResult(1,"成功退出",null);
            }else{
                return new JsonResult(0,"错误访问",null);
            }
        }else{
            return new JsonResult(0,"错误访问",null);
        }
    }

}
