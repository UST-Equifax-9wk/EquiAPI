package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Service
@EnableTransactionManagement
@Transactional(Transactional.TxType.REQUIRED)
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) throws UserAlreadyExistsException{
        if(customerRepository.findByEmail(customer.getEmail()) !=null){
            throw new UserAlreadyExistsException("Email already associated with user. Forgot password?");
        }
        customer.setRole("CUSTOMER");

        return customerRepository.save(customer);

    }


    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
