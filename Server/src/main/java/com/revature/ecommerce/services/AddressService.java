package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveOrUpdate(Address address){ return addressRepository.save(address); }

    public void deleteAddress(Address address) { addressRepository.delete(address); }

    public void deleteAddress(Integer id) { addressRepository.deleteById(id); }

    public Address findById(Integer id)
    {
        Optional<Address> found = addressRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ObjectNotFoundException(Address.class, "Address");
        }
    }


}
