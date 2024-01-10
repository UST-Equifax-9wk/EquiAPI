package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void deleteAddressById(@PathVariable String addressId){
        addressService.deleteAddress(Integer.valueOf(addressId));
    }
}
