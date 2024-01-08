package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <Customer, Integer>{
    Customer findUserByEmail(String email);
}
