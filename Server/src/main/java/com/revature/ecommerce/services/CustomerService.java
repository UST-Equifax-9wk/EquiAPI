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
@jakarta.transaction.Transactional(Transactional.TxType.REQUIRED)
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) throws UserAlreadyExistsException{
        if(customerRepository.findByEmail(customer.getEmail()) !=null){
            throw new UserAlreadyExistsException("Email already associated with user. Forgot password?");
        }
        customer.setPassword(hash(customer.getPassword()));


        return customerRepository.save(customer);
        //isMatch($Person/EmailAddress ,'^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$')
    }

    public Boolean authenticateUser(Customer customer) throws UserDoesNotExistException{

        Customer authCustomer = customerRepository.findByEmail(customer.getEmail());
        if(authCustomer ==null){
            throw new UserDoesNotExistException("This customer is not in database");

        }

        if(checkHash(customer.getPassword(), authCustomer.getPassword())){
            return true;
        }
        return false;
    }


    public String hash(String text) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(text, salt);
    }
    public boolean checkHash(String text, String hash) {
//        return text.equals(hash);
        return BCrypt.checkpw(text, hash);
    }


}