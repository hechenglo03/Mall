package com.hcl.Mall.repository;

import com.hcl.Mall.dao.Product;
import com.hcl.Mall.query.ProductBillMessage;
import com.hcl.Mall.query.ProductMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select new com.hcl.Mall.query.ProductMessage(p.id,p.title,p.price,p.pic,p.sold) from Product p order by p.sold ASC ")
    Page<ProductMessage> findSomeProduct(Pageable pageable);

    @Query("select new com.hcl.Mall.query.ProductMessage(p.id,p.title,p.price,p.pic,p.sold) from Product p where p.sold >= 0")
    Page<ProductMessage> findSomeProductNotBought(Pageable pageable);

    @Query("select new com.hcl.Mall.query.ProductMessage(p.id,p.title,p.price,p.pic,p.sold) from Product p where p.sold > 0")
    Page<ProductMessage> findCarListProduct(Pageable pageable);

    @Query("select new com.hcl.Mall.query.ProductBillMessage(p.id,p.title,p.price,p.pic,-p.sold,p.buytime) from Product p where p.sold < 0")
    Page<ProductBillMessage> findSomeProductBought(Pageable pageable);


    Product getById(long id);

    @Modifying
    @Query("update Product p set p.sold = p.sold + ?1 where p.id = ?2")
    void updateSum(int sum,long id);

    @Modifying
    @Query("update Product p set p.sold = -p.sold,p.buytime = current_timestamp where p.id = ?1")
    void bought(long id);

    @Query("select p.pic from Product p where p.id = ?1")
    String getPicName(long id);

    
 }
