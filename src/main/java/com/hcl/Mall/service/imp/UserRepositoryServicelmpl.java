package com.hcl.Mall.service.imp;

import com.hcl.Mall.dao.User;
import com.hcl.Mall.repository.UserRepository;
import com.hcl.Mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryServicelmpl implements UserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String username,String password) {
        return this.userRepository.getUserByPasswordAndUsername(password,username);
    }
}
