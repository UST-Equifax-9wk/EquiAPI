package com.revature.ecommerce.services;

import com.revature.ecommerce.dto.CustomerDto;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.interfaces.CustomerDetails;
import com.revature.ecommerce.mappers.CustomerMapper;
import com.revature.ecommerce.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;


@Service
@EnableTransactionManagement
@Transactional(Transactional.TxType.REQUIRED)
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
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
     * View customer by entering email
     * @param email
     * @return
     */
    public Customer viewCustomer(String email){
        return customerRepository.findByEmail(email);
    }



    /**
     * Unsure if this must be used?
     * @param email
     * @return
     */
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Optional<CustomerDetails> findCustomerDetailsByCustomerId(Integer customerId) {
        return customerRepository.findCustomerDetailsByCustomerId(customerId);
    }

    public Optional<Customer> findById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    /**
     *
     * @param customerDto
     */
    public void updateCustomer(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = findById(customerDto.customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customerMapper.updateCustomerFromDto(customerDto, customer);
            customerRepository.save(customer);
        } else {
            throw new ObjectNotFoundException(Customer.class, "Customer");
        }
    }
}
