package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.repositories.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
    private final ProductService productService;

    public Seller findById(Integer sellerId) {
        Optional<Seller> found = sellerRepository.findById(sellerId);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ObjectNotFoundException(Seller.class, "Seller");
        }
    }

    public Seller save(Seller seller) {
        seller.setRole("SELLER");
//        seller.setPassword(seller.getPassword()); TODO: ADD Bcrypt
        return sellerRepository.save(seller);
    }

    public Product addProduct(Integer sellerId, Product product) {
        Seller seller = findById(sellerId);
        product.setSeller(seller);
        return productService.addProduct(product);
    }

    public Product updateInventory(Integer productId, Integer inventory) {
        return productService.updateInventory(productId, inventory);
    }

    public Seller findByEmail(String email){
        return sellerRepository.findByEmail(email);
    }
}
