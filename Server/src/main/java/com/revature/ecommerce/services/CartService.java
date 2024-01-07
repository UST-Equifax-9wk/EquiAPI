package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.Inventory;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final InventoryService inventoryService;

    @Autowired
    public CartService(CartRepository cartRepository, InventoryService inventoryService){
        this.cartRepository = cartRepository;
        this.inventoryService = inventoryService;
    }

    /**
     *NOTES: Cart is created at the instance of a User(Customer)
     * @param inventory
     * @param customerId
     * @return
     * @throws UnableToAddItemException
     */
    public Cart addToCart(Inventory inventory, Integer customerId) throws UnableToAddItemException {
        //service to collect item ID
        String productId = inventory.getProductId();
        Cart userCart = cartRepository.findCartByCustomerId(customerId);
        List<String> presentIds = userCart.getProductId();
        presentIds.add(productId);
        userCart.setProductId(presentIds);
        cartRepository.save(userCart);

        return userCart;
    }

    public void removeFromCart(Inventory inventory, Integer customerId) throws UnableToDeleteItemException {
        String productId = inventory.getProductId();
        Cart userCart = cartRepository.findCartByCustomerId(customerId);
        List<String> presentIds = userCart.getProductId();
        presentIds.remove(productId);
        userCart.setProductId(presentIds);
        cartRepository.save(userCart);
    }

    public List<Inventory> getAllCartItems(Integer customerId){
        List<Inventory> inventories = null;
        Cart userCart = cartRepository.findCartByCustomerId(customerId);
        List<String> productIds = userCart.getProductId();
        for(String e : productIds){
                inventories.add(inventoryService.findProductByProductId(e));
        }
        return inventories;
    }
}
