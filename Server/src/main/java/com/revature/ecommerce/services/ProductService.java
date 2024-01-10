package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.repositories.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Set<Product> getAllProducts() {
        return new HashSet<>();
    }

    public Set<Product> getAllProducts(Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        productRepository.findAll(paging);
        return new HashSet<>();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateInventory(Integer productId, Integer inventory) {
        Product product = findById(productId);
        product.setInventory(inventory);
        return productRepository.save(product);
    }

    public Product addItemToProducts (Product product){
        return productRepository.save(product);
    }

    public Product findById(Integer id) {
        Optional<Product> found = productRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ObjectNotFoundException(Product.class, "Product");
        }
    }
}
