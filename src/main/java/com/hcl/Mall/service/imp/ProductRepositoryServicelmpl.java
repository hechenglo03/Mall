package com.hcl.Mall.service.imp;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.query.ProductBillMessage;
import com.hcl.Mall.query.ProductMessage;
import com.hcl.Mall.repository.ProductRepository;
import com.hcl.Mall.service.*;
import com.hcl.Mall.utls.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductRepositoryServicelmpl implements ProductRepositoryService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductMessage> findSomeProduct(Pageable pageable) {
        return this.productRepository.findSomeProduct(pageable).getContent();
    }

    @Override
    public List<ProductMessage> findSomeProductNotBought(Pageable pageable) {
        return this.productRepository.findSomeProductNotBought(pageable).getContent();
    }

    /**
     * 获取已经购买的商品
     * @param pageable
     * @return
     */
    @Override
    public List<ProductBillMessage> findSomeProductBought(Pageable pageable) {
        return this.productRepository.findSomeProductBought(pageable).getContent();
    }

    @Override
    public Product getProductById(long id) {
        return this.productRepository.getById(id);
    }


    public void save(Product product){
        this.productRepository.save(product);
    }



    @Transactional
    public void updatesum(int sum, long id){
        this.productRepository.updateSum(sum,id);
    }

    @Transactional
    @Override
    public void bought(long id) {
        this.productRepository.bought(id);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        String picname = this.productRepository.getPicName(id);
        this.productRepository.deleteById(id);
        return FileUtil.deletePic(picname);
    }

    @Override
    public List<ProductMessage> getCarList(Pageable pageable) {
        return this.productRepository.findCarListProduct(pageable).getContent();
    }


}
