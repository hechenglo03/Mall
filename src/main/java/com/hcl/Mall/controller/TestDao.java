package com.hcl.Mall.controller;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class TestDao {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 6)
    private String title;

    private String url;

    private String price;


}
