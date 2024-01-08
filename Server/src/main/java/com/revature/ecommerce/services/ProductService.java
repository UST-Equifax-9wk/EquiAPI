package com.revature.ecommerce.services;

import com.revature.ecommerce.controllers.ProductController;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product addItemToProducts (Product product){
        Product newProduct = new Product(product.getType(), product.getQuantity(),
                product.getName(),product.getDescription());

        return productRepository.save(newProduct);
    }
}
