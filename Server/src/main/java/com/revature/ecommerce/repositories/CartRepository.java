package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository <Cart, Integer>{

//    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE customer_id=?1")

//    Cart findCartByCustomerId(Integer customerId);
//    Set<Cart> findAllCartByCustomerId(Integer customerId);

}
