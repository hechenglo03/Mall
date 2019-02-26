package com.hcl.Mall.repository;

import com.hcl.Mall.dao.Buyer;
import com.hcl.Mall.dao.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {

    Buyer getBuyerByPasswordAndBuyername(String password,String username);
}
