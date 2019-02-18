package com.hcl.Mall.repository;

import com.hcl.Mall.dao.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller getBySellernameAndSellerpassword(String sellername,String password);
}
