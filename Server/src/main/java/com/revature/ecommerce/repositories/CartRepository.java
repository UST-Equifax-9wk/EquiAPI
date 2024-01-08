package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository <Cart, Integer>{

//    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE customer_id=?1")
    Optional<Cart> findByUserId(Integer userId);
}
