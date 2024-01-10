package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(name = "pg_no", defaultValue = "0") Integer pageNum,
            @RequestParam(name = "pg_size", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(productService.getAllProducts(pageNum, pageSize));
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody Product product) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String productId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
