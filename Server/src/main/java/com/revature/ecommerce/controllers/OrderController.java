package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.MakeOrder;
import com.revature.ecommerce.entities.Order;
import com.revature.ecommerce.services.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping(path = "/customer/{email}/make-order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order makeAnOrder(@PathVariable String email, @RequestBody MakeOrder makeOrder, HttpServletResponse response){
        return orderService.makeAnOrder(email, makeOrder);
    }

    @GetMapping(path="/customer/view-order/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Order viewOrder(@PathVariable Integer orderNumber){
        return orderService.viewOrder(orderNumber);
    }
}
