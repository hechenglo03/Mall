package com.hcl.Mall.service;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.query.ProductMessage;

import com.hcl.Mall.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ProductRepositoryService  {

    List<ProductMessage> findSomeProduct(Pageable pageable);

    List<ProductMessage> findSomeProductNotBought(Pageable pageable);

    List<ProductMessage> findSomeProductBought(Pageable pageable);

    Product getProductById(long id);

    void save(Product product);

    void updatesum(int sum,long id);

    void bought(long id);

    boolean delete(long id);
}
