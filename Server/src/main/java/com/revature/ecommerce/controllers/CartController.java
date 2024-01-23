package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.AddToCart;
import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.services.CartService;
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
public class CartController {

    private final CartService cartService;
    private JwtUtil jwtUtil;
    private CookieUtil cookieUtil;

    @Autowired
    public CartController(CartService cartService, JwtUtil jwtUtil, CookieUtil cookieUtil){
        this.cartService = cartService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping("/customers/cart/add-to-cart")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Set<Cart> addProduct(@RequestBody AddToCart addToCart, HttpServletRequest request, HttpServletResponse response)
    throws UnableToAddItemException {
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        cartService.addProductToCart(email, addToCart);
        Set<Cart> cart =viewAllCart(request);
        return cart;
    }

    @GetMapping("/customers/cart/view-cart")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public Set<Cart> viewAllCart(HttpServletRequest request){
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return cartService.viewCart(email);
    }

    @DeleteMapping(path = "/customers/cart/remove-item/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<Cart> deleteFromCart
            (@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnableToDeleteItemException {
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        cartService.removeProductFromCart(id, email);
        return viewAllCart(request);

    }

    @GetMapping(path = "/customers/cart/cart-total")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Double cartTotal(HttpServletRequest request, HttpServletResponse response){
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return cartService.cartTotal(email);
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