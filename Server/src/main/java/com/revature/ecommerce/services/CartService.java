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
    public Cart addProductToCart (AddToCart addToCart) throws UnableToAddItemException{
        Customer customer = customerRepository.findByEmail(addToCart.getCustomerEmail());

        Product product = productRepository.getReferenceById(addToCart.getProductId());

        if(customerRepository.findByEmail(addToCart.getCustomerEmail())==null) {
            throw new UnableToAddItemException("User does not exist");
            }

        System.out.println(addToCart.getQuantity());

        if (addToCart.getQuantity() > product.getInventory()) {
            addToCart.setQuantity(product.getInventory());
        }

        Customer presentCustomer = customerRepository.findByEmail(addToCart.getCustomerEmail());
        Set<Cart> carts = presentCustomer.getCart();
        for(Cart c : carts){
            if(c.getProductId().equals(product.getProductId())){
                c.setQuantity(c.getQuantity() + addToCart.getQuantity());
                return cartRepository.save(c);
            }
        }

        Cart cart = new Cart();
        cart.setQuantity(addToCart.getQuantity());
        cart.setProductId(product.getProductId());
        cart.setTotalPrice(addToCart.getPrice()* addToCart.getQuantity());
        cart.setPrice(addToCart.getPrice());

        cart.setCustomer(customer);

        int newNumber = product.getInventory() - addToCart.getQuantity();

        if(newNumber < 0){newNumber = 0;}

        product.setInventory(newNumber);
        productRepository.save(product);

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

                if(c.getQuantity() - addToCart.getQuantity()<0){
                    throw new UnableToDeleteItemException("Quantity is more than on cart");
                }

                c.setQuantity(c.getQuantity() - addToCart.getQuantity());
                c.setTotalPrice(c.getPrice()*c.getQuantity());
                product.setInventory(product.getInventory() + addToCart.getQuantity());
                productRepository.save(product);
                if (c.getQuantity() == 0) {
                    cartRepository.delete(c);
                    break;
                }

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

    public Double getTotal(String email){
        Double totalCost = 0.0;
        Customer customer = customerRepository.findByEmail(email);
        Set<Cart> customerCart = customer.getCart();
        for(Cart c : customerCart){
            totalCost = totalCost + c.getTotalPrice();
        }
        return totalCost;
    }

}