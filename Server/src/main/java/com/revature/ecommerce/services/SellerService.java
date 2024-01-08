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

    public Seller findById(String sellerId) {
        Optional<Seller> found = sellerRepository.findById(Integer.valueOf(sellerId));
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ObjectNotFoundException(Seller.class, "Seller");
        }
    }

    public void save(Seller seller) {
        sellerRepository.save(seller);
    }

    public Product addProduct(String sellerId, Product product) {
        Seller seller = findById(sellerId);
        product.setSeller(seller);
        return productService.addProduct(product);
    }
}
