package com.hcl.Mall.dao;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    //用户名
    private String username;

    //密码，用md5加密后
    private String password;

    //昵称
    private String nickname;


}
