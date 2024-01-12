package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.services.CartService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CartController {

    private final CartService cartService;

    @Autowired

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/users/{email}/add-to-cart")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Cart addProduct(@PathVariable String email, @RequestBody Cart cart, HttpServletResponse response)

    throws UnableToAddItemException {
        return cartService.addProductToCart(cart);
    }
}