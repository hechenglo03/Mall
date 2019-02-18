package com.hcl.Mall.query;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *主页显示商品的信息
 *
 */
@Data
@AllArgsConstructor
public class ProductMessage {

    private long id;

    private String title;

    private double price;

    private String pic;

    private int sold;
}
