package com.hcl.Mall.service.imp;

import com.hcl.Mall.dao.Buyer;
import com.hcl.Mall.repository.BuyerRepository;
import com.hcl.Mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerRepositoryServicelmpl implements BuyerRepositoryService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Buyer getBuyer(String username, String password) {
        return this.buyerRepository.getBuyerByPasswordAndBuyername(password,username);
    }
}
