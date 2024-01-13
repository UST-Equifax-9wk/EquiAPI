package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.services.AddressService;
import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(path = "/addresses")
public class AddressController {

    private final AddressService addressService;
    private final JwtUtil jwUtil;
    private final CookieUtil cookieUtil;

    @Autowired
    public AddressController(AddressService addressService, JwtUtil jwtUtil, CookieUtil cookieUtil) {
        this.addressService = addressService;
        this.jwUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
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


    @PostMapping(path = "/add-address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Address addAddressToCustomer(@RequestBody Address address,
                                        HttpServletResponse response, HttpServletRequest request)
            throws UnableToAddItemException {
        String email = jwUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return addressService.addAddress(email, address);
    }

    @GetMapping(path = "/get-address")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Set<Address> viewAddress(HttpServletResponse response,HttpServletRequest request ){
        String email = jwUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return addressService.viewAddresses(email);
    }

    @PostMapping(path = "/delete-address")
    @ResponseStatus(HttpStatus.OK)
    public Address deleteAnAddress(@RequestBody Address address,
                                 HttpServletResponse response, HttpServletRequest request){
        String email = jwUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return addressService.deleteAddress(email, address);
    }

}
