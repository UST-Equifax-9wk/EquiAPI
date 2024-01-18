package com.revature.ecommerce.services;

import com.revature.ecommerce.dto.AddToCart;
import com.revature.ecommerce.entities.Cart;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.ProductRepository;
import com.revature.ecommerce.repositories.CustomerRepository;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@EnableTransactionManagement
@Transactional(Transactional.TxType.REQUIRED)
public class CartService {

    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;


    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository,
                       ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    /**
     *
     * @param addToCart
     * Add to cart has 3 fields: CustomerEmail, ProductId and Quantity
     * @return
     * @throws UnableToAddItemException
     */
    public Cart addProductToCart (String email, AddToCart addToCart) throws UnableToAddItemException{
        Customer customer = customerRepository.findByEmail(email);
        Product product = productRepository.getReferenceById(addToCart.getProductId());

        Set<Cart> carts = customer.getCart();
        for(Cart c : carts){
            if(c.getProductId().equals(product.getProductId())){
                throw new UnableToAddItemException("Item already in cart");
            }
        }

        Cart cart = new Cart();
        cart.setProductId(addToCart.getProductId());
        cart.setPrice(addToCart.getPrice());
        cart.setCustomer(customer);

        return cartRepository.save(cart);
    }

    /**
     *
     * @param email
     * @param addToCart
     * Add to cart has 3 fields: CustomerEmail, ProductId and Quantity. When deleting items, limit to what is in cart
     * @return
     */
    public Set<Cart> removeProductFromCart (String email, AddToCart addToCart) throws UnableToDeleteItemException{
        Product product = productRepository.getReferenceById(addToCart.getProductId());
        Set<Cart> carts = cartRepository.findAllCartByCustomerId(customerRepository.findByEmail(email).getCustomerId());
        for (Cart c : carts) {
            if (c.getProductId().equals(product.getProductId())) {
                    cartRepository.delete(cartRepository.findCartByCartId(c.getCartId()));
                    break;
                }
            }
        return carts;
    }

    /**
     *
     * @param email
     * @return type Set
     */

    public Set<Cart> viewCart (String email) {
        Customer customer = customerRepository.findByEmail(email);
        return customer.getCart();
    }


}