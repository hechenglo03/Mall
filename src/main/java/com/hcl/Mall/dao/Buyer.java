package com.hcl.Mall.dao;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Buyer {

    @Id
    @GeneratedValue
    private long id;

    //用户名
    private String buyername;

    //密码，用md5加密后
    private String password;

    //昵称
    private String nickname;

    public String toString(){
        return "用户正在登录  用户名称为"+this.buyername;
    }
}
