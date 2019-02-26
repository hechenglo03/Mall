package com.hcl.Mall.dao;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    //商品的标题
    private String title;

    //商品的摘要
    private String summary;

    //当前商品的价格
    private double price;

    //图片地址url
    private String pic;

    //商品已经卖出去的数量
    // <0 表示已购买了，>0表示几件加入购物车
    private int sold;

    //正文内容
    @Lob
    private String content;

    //购买的时间
    private Date buytime;

}
