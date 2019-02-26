package com.hcl.Mall.controller;


import com.hcl.Mall.dao.Product;
import com.hcl.Mall.service.ProductRepositoryService;
import com.hcl.Mall.utls.FileUtil;
import com.hcl.Mall.utls.JsonResult;
import com.hcl.Mall.utls.MallConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/editor")
public class Editor {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @PostMapping("/picdir")
    @ResponseBody
    public JsonResult acceptFile(@RequestParam("file") MultipartFile file){
        log.info("上传商品图片"+file.getOriginalFilename());
        String result = FileUtil.SavePic(file);
        if(result == null){
            log.info("上传文件失败");
            return new JsonResult(0,"上传失败",null);
        }
        log.info("上传文件成功");
        return new JsonResult(1,"上传成功",result);
    }

    /**
     * 商品信息录入
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public JsonResult acceptProduct(Product product){
        this.productRepositoryService.save(product);
        return new JsonResult(1,"成功录入商品","/home");
    }


    @GetMapping("")
    public String editor(){
        return "Editor";
    }

    /**
     * 获取商品信息的编辑页面
     * @param session
     * @param id
     * @return
     */
    @GetMapping("/index")
    public String ProductEditor(HttpSession session,@RequestParam("id") long id){
        session.setAttribute(MallConfig.PRODUCT_SESSION_KEY,id);
        return "publish";
    }

    @ResponseBody
    @GetMapping("/product")
    public JsonResult getProductMessage(HttpSession session){
        if(session.getAttribute(MallConfig.PRODUCT_SESSION_KEY) != null){
            long id = (long) session.getAttribute(MallConfig.PRODUCT_SESSION_KEY);
            Product product = this.productRepositoryService.getProductById(id);
            return new JsonResult(1,"获取商品的信息",product);
        }else{
            return new JsonResult(0,"获取商品信息失败",null);
        }

    }

}
