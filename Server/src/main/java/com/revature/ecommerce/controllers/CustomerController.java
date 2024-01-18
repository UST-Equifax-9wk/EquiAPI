package com.revature.ecommerce.controllers;
import com.revature.ecommerce.dto.CustomerDto;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.interfaces.CustomerDetails;
import com.revature.ecommerce.services.CustomerService;
import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
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

    @GetMapping(path = "/customers/{customerId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER') and @customerAccessControl.checkCustomer(#request, #customerId)")
    public ResponseEntity<CustomerDetails> getCustomer(HttpServletRequest request, @PathVariable String customerId){
        Optional<CustomerDetails> optionalCustomer = customerService.findCustomerDetailsByCustomerId(Integer.valueOf(customerId));
        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(optionalCustomer.get());
        } else {
            throw new ObjectNotFoundException(Customer.class, "Customer");
        }
    }

    @PutMapping(path = "/customers/{customerId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER') and @customerAccessControl.checkCustomer(#request, #customerId)")
    public ResponseEntity<String> updateCustomer(HttpServletRequest request, @PathVariable String customerId, @RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
        return ResponseEntity.ok("Updated");
    }
}
