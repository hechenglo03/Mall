package com.hcl.Mall.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.MySQLInnoDBDialect;
import org.springframework.stereotype.Component;


public class MysqlConfig extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";

    }
}

