package com.hcl.Mall.service.imp;

import com.hcl.Mall.dao.Seller;
import com.hcl.Mall.repository.SellerRepository;
import com.hcl.Mall.service.SellerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerRepositoryServicelmp implements SellerRepositoryService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller getSeller(String sellername, String password) {
        return this.sellerRepository.getBySellernameAndSellerpassword(sellername,password);
    }
}
