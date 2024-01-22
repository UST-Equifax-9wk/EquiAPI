package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository <Cart, Integer>{

    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE customer_id=?1")
    Set<Cart> findAllCartByCustomerId(Integer customerId);

    @Query(nativeQuery = true, value = "DELETE FROM carts WHERE customer_id=?1")
    void deleteAllByCustomerId(Integer customerId);

    Cart findCartByCartId(Integer cartId);

    @Query(nativeQuery = true, value = "SElECT * FROM carts WHERE customer_id=?1 AND cart_id=?2")
    Cart findCartByCustomerID(Integer customerId, Integer cartId);
}
