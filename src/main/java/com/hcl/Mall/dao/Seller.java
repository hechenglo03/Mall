package com.hcl.Mall.dao;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Seller {

    @Id
    @GeneratedValue
    private long id;

    private String sellername;

    private String sellerpassword;

    private String nickname;

    public String toString(){
        return "卖者正在登录 登录名为"+this.sellername;
    }
}
