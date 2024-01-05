package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.repositories.CartRepository;

public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
    //add to cart
    public Cart addToCart(Cart cart){
        try{
            cartRepository.save(cart);
        } catch (UnableToAddItemException e){
            System.out.println("Unable to add item");
        }
    }
}
