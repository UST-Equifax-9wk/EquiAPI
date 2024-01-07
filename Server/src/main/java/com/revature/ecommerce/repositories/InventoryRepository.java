package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Integer>{

    @Query(nativeQuery = true, value = "SELECT * FROM inventory WHERE productId = ?1")
    Inventory findProductByProductId(String productId);
}
