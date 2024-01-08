package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.ProductRepository;
import com.revature.ecommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@EnableTransactionManagement
@Transactional(Transactional.TxType.REQUIRED)
public class CartService {
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository,
                       ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Cart addProductToCart (Cart cart, String email) throws UnableToAddItemException{
        Product product = productRepository.findProductByProductId(cart.getProductId());
        if(product.getQuantity() < cart.getQuantity()){
            throw new UnableToAddItemException("Quantity available is less than your request");
        }
        Customer customer = userRepository.findUserByEmail(email);
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }

    public Set<Cart> removeProductFromCart (Cart cart, String email) throws UnableToDeleteItemException {
        Customer customer = userRepository.findUserByEmail(email);
        Set<Cart> itemsInCart = cartRepository.findAllCartByCustomerId(customer.getCustomerId());
        if(!itemsInCart.remove(cart)){
            throw new UnableToDeleteItemException("Item not in cart");
        }
        return itemsInCart;
    }

    public Set<String> viewCart (String email){
        Customer customer = userRepository.findUserByEmail(email);
        Set<Cart> itemsInCart = cartRepository.findAllCartByCustomerId(customer.getCustomerId());
        Set<String> allItems = new HashSet<>();
        for(Cart c : itemsInCart){
            Product product = productRepository.findProductByProductId(c.getProductId());
            String [] itemData = {String.valueOf(product.getProductId()), product.getName(),
                    product.getDescription(), product.getType()};
            allItems.add(Arrays.toString(itemData));
        }
        return allItems;
    }
}
