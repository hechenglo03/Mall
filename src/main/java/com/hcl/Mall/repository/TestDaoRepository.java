package com.hcl.Mall.repository;

import com.hcl.Mall.controller.TestDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TestDaoRepository extends JpaRepository<TestDao,Long> {

    @Modifying
    @Query("update TestDao t set t.title = ?1 where t.id = 1")
    void update(String title);
}
