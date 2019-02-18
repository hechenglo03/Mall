package com.hcl.Mall.repository;

import com.hcl.Mall.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User getUserByPasswordAndUsername(String password,String username);
}
