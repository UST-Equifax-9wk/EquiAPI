package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.interfaces.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer>{

    Customer findByEmail(String email);
    Optional<CustomerDetails> findCustomerDetailsByCustomerId(Integer customerId);
}
