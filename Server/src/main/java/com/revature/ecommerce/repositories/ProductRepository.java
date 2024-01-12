package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>{
    List<Product> findBySellerId(Integer sellerId);
    Page<Product.ProductCard> findAllProjectedBy(Pageable pageable);

    @Query(nativeQuery = true, value = "UPDATE products SET inventory = ?1 WHERE id = ?2")
    void updateInventoryNumber(Integer newNumber, Integer id);
}
