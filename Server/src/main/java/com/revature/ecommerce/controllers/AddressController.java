package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.services.AddressService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(path = "/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(path = "/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddress(@PathVariable String addressId){
        return addressService.findById(Integer.valueOf(addressId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Address addAddress(@RequestBody Address address){
        return addressService.saveOrUpdate(address);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@RequestBody Address address){
        addressService.deleteAddress(address);
    }
    @DeleteMapping(path = "/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddressById(@PathVariable Integer addressId){
        addressService.deleteAddressById(addressId);
    }


    @PostMapping(path = "/{email}/add-address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Address addAddressToCustomer(@PathVariable String email,
                                    @RequestBody Address address, HttpServletResponse response)
            throws UnableToAddItemException {

        return addressService.addAddress(email, address);
    }

    @GetMapping(path = "/{email}/get-address")
    @ResponseStatus(HttpStatus.OK)
    public Set<Address> viewAddress(@PathVariable String email, HttpServletResponse response){
        return addressService.viewAddresses(email);
    }

    @PostMapping(path = "/{email}/delete-address")
    @ResponseStatus(HttpStatus.OK)
    public Address deleteAnAddress(@PathVariable String email,
                                 @RequestBody Address address,
                                 HttpServletResponse response){
        return addressService.deleteAddress(email, address);
    }

    /**
     * REMEMBER TO DO: CHECK IF CONFLICTS ARISE WITH ADDRESSES IN CUSTOMER'S CONSTRUCTOR
     */
}
