package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer>{
}
