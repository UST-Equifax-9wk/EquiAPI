package com.revature.ecommerce.controllers;

import com.revature.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

//    @PostMapping("/products/add-product")
//    @ResponseStatus(HttpStatus.OK)
//    public Product addProduct(@RequestBody Product product, HttpServletResponse response){
//        return productService.addItemToProducts (product);
//    }

}
