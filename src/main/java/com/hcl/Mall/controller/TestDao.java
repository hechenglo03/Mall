package com.hcl.Mall.controller;

import lombok.Data;

@Data
public class TestDao {
    private long id;

    private String title;

    private String url;

    private String price;

    public static void main(String[] args){
        String[] list = "/a/b/c".split("/");
        System.out.println(list.length);
    }


}
