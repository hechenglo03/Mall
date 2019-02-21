package com.hcl.Mall.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


/**
 * 账单显示的商品细节
 */
@Data
@AllArgsConstructor
public class ProductBillMessage {

    private long id;

    private String title;

    private double price;

    private String pic;

    private int sold;

    private Date buytime;
}
