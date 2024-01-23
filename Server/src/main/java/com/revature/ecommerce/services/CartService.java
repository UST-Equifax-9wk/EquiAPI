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
    public void addProductToCart (String email, AddToCart addToCart) throws UnableToAddItemException{
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
        cart.setProductName(product.getName());
        cartRepository.save(cart);
        customer.getCart().add(cart);
        customerRepository.save(customer);

    }

    /**
     *
     *
     * Add to cart has 3 fields: CustomerEmail, ProductId and Quantity. When deleting items, limit to what is in cart
     * @return
     */
    public void removeProductFromCart (Integer cartId, String email) throws UnableToDeleteItemException{
        Customer customer = customerRepository.findByEmail(email);
        Cart item = cartRepository.findCartByCustomerID(customer.getCustomerId(), cartId);
        if(item == null) {
            throw new UnableToDeleteItemException("Could not delete Item");
        } else {
            cartRepository.deleteById(cartId);
        }
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

    public Double cartTotal(String email){
        Customer customer = customerRepository.findByEmail(email);
        Set<Cart> carts = customer.getCart();
        Double cartTotal = 0.0;

        for(Cart c : carts){
            cartTotal += c.getPrice();
        }
        return cartTotal;
    }


}