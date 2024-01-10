package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Cart;

import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.ProductRepository;
import com.revature.ecommerce.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

    public Cart addProductToCart (Cart cart) throws UnableToAddItemException{
        return cartRepository.save(cart);
    }

//    public Set<Cart> removeProductFromCart (Cart cart, Product product) throws UnableToDeleteItemException {
//
//    }

//    public Set<String> viewCart (String email) {
//        Customer customer = customerRepository.findByEmail(email);
//        Set<Cart> itemsInCart = cartRepository.findAllCartByCustomerId(customer.getCustomerId());
//        Set<String> allItems = new HashSet<>();
//        for (Cart c : itemsInCart) {
//            Product product = productRepository.findById(c.getProductId()).get();
//            String[] itemData = {String.valueOf(product.getProductId()), product.getName(),
//                    product.getDescription(), product.getType()};
//            allItems.add(Arrays.toString(itemData));
//        }
//        return allItems;
//    }

//        Product product = productRepository.findProductByProductId(cart.getProductId());
//        if(product.getQuantity() < cart.getQuantity()){
//            throw new UnableToAddItemException("Quantity available is less than your request");
//        }
//        Customer user = customerRepository.findUserByEmail(email);
//        cart.setCustomer(user);
//        return cartRepository.save(cart);
//        return new Cart();
//    }

//    public Set<Cart> removeProductFromCart (Cart cart, String email) throws UnableToDeleteItemException {
////        Customer user = customerRepository.findUserByEmail(email);
////        Optional<Cart> itemsInCart = cartRepository.findByUserId(user.getUserId());
////        if(!itemsInCart.remove(cart)){
////            throw new UnableToDeleteItemException("Item not in cart");
////        }
////        return itemsInCart;
//        return new HashSet<>();
//    }

//    public Set<String> viewCart (String email){
////        Customer user = customerRepository.findUserByEmail(email);
////        Optional<Cart> itemsInCart = cartRepository.findByUserId(user.getUserId());
////        Set<String> allItems = new HashSet<String>();
////        for(Cart c : itemsInCart){
////            Product product = productRepository.findProductByProductId(c.getProductId());
////            String [] itemData = {String.valueOf(product.getProductId()), product.getName(),
////                    product.getDescription(), product.getType()};
////            allItems.add(Arrays.toString(itemData));
////        }
////        return allItems;
//        return new HashSet<>();
//
//    }
}