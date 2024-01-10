package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.SellerResponse;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.services.SellerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/sellers")
public class SellerController {
    private final SellerService sellerService;

    @GetMapping(path = "/{sellerId}")
    public ResponseEntity<SellerResponse> getSeller(@PathVariable String sellerId) {
        Seller seller = sellerService.findById(Integer.valueOf(sellerId));
        SellerResponse response = new SellerResponse(
                seller.getId(),
                seller.getFirstName(),
                seller.getLastName(),
                seller.getEmail()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{sellerId}/products")
    public ResponseEntity<Product> addNewProduct(@PathVariable String sellerId, @RequestBody Product product) {
        return ResponseEntity.ok(sellerService.addNewProduct(Integer.valueOf(sellerId), product));
    }

    @GetMapping(path = "/{sellerId}/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable String sellerId) {
        return ResponseEntity.ok(sellerService.getProducts(Integer.valueOf(sellerId)));
    }
}
