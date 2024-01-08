package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products/add-product")
    @ResponseStatus(HttpStatus.OK)
    public Product addProduct(@RequestBody Product product, HttpServletResponse response){
        return productService.addItemToProducts (product);
    }

}
