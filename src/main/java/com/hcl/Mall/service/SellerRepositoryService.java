package com.hcl.Mall.service;

import com.hcl.Mall.dao.Seller;

public interface SellerRepositoryService {

    Seller getSeller(String sellername,String password);
}
