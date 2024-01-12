package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.AddToCart;
import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.services.CartService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CartController {

    private final CartService cartService;

    @Autowired

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/customers/{email}/add-to-cart")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Cart addProduct(@PathVariable String email, @RequestBody AddToCart addToCart, HttpServletResponse response)


    throws UnableToAddItemException {
        return cartService.addProductToCart(addToCart);
    }

    @GetMapping("/customers/{email}/view-cart")
    @ResponseStatus(HttpStatus.OK)
    public Set<Cart> viewAllCart(@PathVariable String email){
        return cartService.viewCart(email);
    }

    @PostMapping(path = "/customers/{email}/remove-item")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<Cart> deleteFromCart
            (@PathVariable String email,
             @RequestBody AddToCart addToCart, HttpServletResponse response) throws UnableToDeleteItemException {
        return cartService.removeProductFromCart(email, addToCart);
    }
    @GetMapping(path = "/customer/{email}/cart/get-total")
    @ResponseStatus(HttpStatus.OK)
    public Double getTotalCost(@PathVariable String email){
        return cartService.getTotal(email);
    }


    /**
     * Error handling: UnableToAddItemException
     * @param e
     * @return
     */
    @ExceptionHandler(UnableToAddItemException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String UnableToAddItemExceptionHandler (UnableToAddItemException e){
        return e.getMessage();
    }

    /**
     * Error handling: UnableToDeleteItemException
     * @param e
     * @return
     */

    @ExceptionHandler(UnableToDeleteItemException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String UnableToDeleteItemExceptionHandler(UnableToDeleteItemException e){
        return e.getMessage();
    }
}