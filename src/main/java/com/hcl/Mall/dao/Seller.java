package com.hcl.Mall.dao;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Seller {

    @Id
    @GeneratedValue
    private long id;

    private String sellername;

    private String sellerpassword;

    private String nickname;
}
