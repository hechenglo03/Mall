package com.hcl.Mall.controller;


import com.hcl.Mall.dao.Product;
import com.hcl.Mall.service.ProductRepositoryService;
import com.hcl.Mall.utls.FileUtil;
import com.hcl.Mall.utls.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/editor")
public class Editor {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @PostMapping("/picdir")
    @ResponseBody
    public JsonResult acceptFile(@RequestParam("file") MultipartFile file){
        String result = FileUtil.SavePic(file);
        if(result == null){
            return new JsonResult(0,"上传失败",null);
        }
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

}
