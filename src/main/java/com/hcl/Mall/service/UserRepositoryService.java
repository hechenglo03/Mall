package com.hcl.Mall.service;

import com.hcl.Mall.dao.User;

public interface UserRepositoryService {

    User getUser(String username,String password);

}
