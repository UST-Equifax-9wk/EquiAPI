package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.repositories.AddressRepository;
import com.revature.ecommerce.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class AddressService {

    private AddressRepository addressRepository;
    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerService customerService,
                          CustomerRepository customerRepository){
        this.addressRepository = addressRepository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }


    public Address saveOrUpdate(Address address){ return addressRepository.save(address); }
//
    public void deleteAddress(Address address) { addressRepository.delete(address); }
//
    public void deleteAddressById(Integer id) { addressRepository.deleteById(id); }

    public Address findById(Integer id)
    {
        Optional<Address> found = addressRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ObjectNotFoundException(Address.class, "Address");
        }
    }


    /**
     * The parameter to set default is pre-set prior to this stage
     * @param email
     * @param address
     * @return
     */
    public Address addAddress(String email, Address address) throws UnableToAddItemException{
        Customer customer = customerService.findByEmail(email);
        Set<Address> customerAddresses = customer.getAddresses();
        for(Address a : customerAddresses){
            if(a.equals(address)){
                throw new UnableToAddItemException("Address already exists");
            }
        }
        address.setCustomer(customer);
        return addressRepository.save(address);
        //What if save fails? Check input data at client-side
    }

    public Address deleteAddress(String email, Address address){
        Customer customer = customerService.findByEmail(email);
        Address deletedAddress = null;
        Set<Address> customerAddresses = customer.getAddresses();
        for(Address a : customerAddresses){
            if(a.equals(address)){
                deletedAddress = a;
            }
        }
        customerAddresses.remove(deletedAddress);
        customer.setAddresses(customerAddresses);

        customerRepository.save(customer);

        addressRepository.save(address);

        return deletedAddress;
    }

    public Set<Address> viewAddresses(String email){
        Customer customer = customerRepository.findByEmail(email);
        return customer.getAddresses();
    }
}
