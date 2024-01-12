package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.services.CustomerService;
import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private JwtUtil jwtUtil;
    private CookieUtil cookieUtil;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService, JwtUtil jwtUtil, CookieUtil cookieUtil){
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @GetMapping(path = "/customer")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Customer viewCustomer(HttpServletRequest request){
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));

        return customerService.findByEmail(email);
    }

}
