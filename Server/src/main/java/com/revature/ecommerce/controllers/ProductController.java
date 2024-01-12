package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.ProductDto;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "")
    public ResponseEntity<List<Product.ProductCard>> getAllProducts(
            @RequestParam(name = "pg_no", defaultValue = "0") Integer pageNum,
            @RequestParam(name = "pg_size", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(productService.getAllProducts(pageNum, pageSize));
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(productService.findById(Integer.valueOf(productId)));
    }

    /**
     * @param productDto Only the productId and the updating fields are required
     *                   Overwriting the existing field with null doesn't work
     * @return the response with details of the updated product and 'ok' status
     */
    @PutMapping(path = "/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "/add-product")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product addProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

}
