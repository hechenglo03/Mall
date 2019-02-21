package com.hcl.Mall.service;

import com.hcl.Mall.controller.TestDao;

public interface TestDaoRepositoryService {

    void save(TestDao testDao);

    void update(String title) throws Exception ;
}
