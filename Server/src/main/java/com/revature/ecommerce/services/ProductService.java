package com.revature.ecommerce.services;

import com.revature.ecommerce.dto.ProductDto;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.repositories.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper
    ){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts(Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        Page<Product> products = productRepository.findAll(paging);
        if (products.hasContent()) {
            return products.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateInventory(Integer productId, Integer inventory) {
        Product product = findById(productId);
        product.setInventory(inventory);
        return productRepository.save(product);
    }

    public Product findById(Integer productId) {
        Optional<Product> found = productRepository.findById(productId);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ObjectNotFoundException(Product.class, "Product");
        }
    }

    /**
     *
     * @param productDto
     * @return
     */
    public Product updateProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productMapper.updateProductFromDto(productDto, product);
            return productRepository.save(product);
        } else {
            throw new ObjectNotFoundException(Product.class, "Product");
        }
    }

    public void deleteProduct(String productId) {
        Product product = findById(Integer.valueOf(productId));
        productRepository.delete(product);
    }
}
