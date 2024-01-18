package com.revature.ecommerce.util;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerAccessControl {
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final CustomerService customerService;

    @Autowired
    public CustomerAccessControl(
            JwtUtil jwtUtil,
            CookieUtil cookieUtil,
            CustomerService customerService
    ) {
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
        this.customerService = customerService;
    }

    public boolean checkCustomer(HttpServletRequest request, String customerId) {
        String email = this.jwtUtil.parseEmail(this.cookieUtil.getCookie(request, "jwt"));
        Customer customer = customerService.findByEmail(email);
        System.out.println("Customer: " + customer);

        if (customer != null) {
            return Objects.equals(customer.getCustomerId(), Integer.valueOf(customerId));
        } else {
            throw new AccessDeniedException("User doesn't have access for this operation.");
        }
    }
}