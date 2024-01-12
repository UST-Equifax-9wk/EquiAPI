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

    /**
     * This takes in Customer which has fields: Email, FirstName, LastName, Password at minimum
     * @param customer
     * @return
     * @throws UserAlreadyExistsException
     */
    public Customer addCustomer(Customer customer) throws UserAlreadyExistsException {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email already associated with user. Forgot password?");
        }
        customer.setRole("CUSTOMER");
        customer.setPassword(hash(customer.getPassword()));

        return customerRepository.save(customer);

    }

    /**
     * A service class method to save a Customer
     * @param customer
     * @return
     */

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    /**
     * Authentication method requires Customer basic fields
     * @param customer
     * @return
     * @throws UserDoesNotExistException
     */
    public Boolean authenticateUser(Customer customer) throws UserDoesNotExistException{

        Customer authCustomer = customerRepository.findByEmail(customer.getEmail());
        if(authCustomer ==null){
            throw new UserDoesNotExistException("This customer is not in database");
        }

        return checkHash(customer.getPassword(), authCustomer.getPassword());
    }

    /**
     * View customer by entering email
     * @param email
     * @return
     */
    public Customer viewCustomer(String email){
        return customerRepository.findByEmail(email);
    }

    /**
     * BCrypt encryption of password
     * @param text
     * @return
     */
    public String hash(String text) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(text, salt);
    }

    /**
     * BCrypt comparison of passwords
     * @param text
     * @param hash
     * @return
     */
    public boolean checkHash(String text, String hash) {
        return BCrypt.checkpw(text, hash);
    }

    /**
     * Unsure if this must be used?
     * @param email
     * @return
     */
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
