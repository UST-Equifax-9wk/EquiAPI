package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final ProductService productService;
//    private final PasswordEncoder passwordEncoder;


    @Autowired
    public SellerService(SellerRepository sellerRepository, ProductService productService) {
        this.sellerRepository = sellerRepository;
        this.productService = productService;
    }

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
//        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        return sellerRepository.save(seller);
    }

    public Product addNewProduct(Integer sellerId, Product product) {
        Seller seller = findById(sellerId);
        product.setSeller(seller);
        return productService.addNewProduct(product);
    }

    public Seller findByEmail(String email){
        return sellerRepository.findByEmail(email);
    }

    public List<Product> getProducts(Integer sellerId) {
        return productService.findBySellerId(sellerId);
    }
}
