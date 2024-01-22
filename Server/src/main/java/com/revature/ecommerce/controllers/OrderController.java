package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.OrderDto;
import com.revature.ecommerce.entities.Order;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.services.OrderService;
import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OrderController {
    private final OrderService orderService;
    private final JwtUtil jwUtil;
    private final CookieUtil cookieUtil;

    @Autowired
    public OrderController(OrderService orderService, JwtUtil jwtUtil, CookieUtil cookieUtil){
        this.orderService = orderService;
        this.jwUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping(path = "/customers/order/checkout")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order makeAnOrder(HttpServletResponse response, HttpServletRequest request)
    throws UnableToAddItemException {
        String email = jwUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return orderService.makeAnOrder(email);
    }

    @GetMapping(path="/customers/view-order/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OrderDto viewOrder(@PathVariable Integer orderNumber){
        return orderService.viewOrder(orderNumber);
    }

    @GetMapping(path="/customers/view-order")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Set<OrderDto> viewCustomerOrder(HttpServletRequest request){
        String email = jwUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return orderService.viewOrderByCustomer (email);
    }
}
