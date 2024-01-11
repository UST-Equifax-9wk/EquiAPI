package com.revature.ecommerce.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/test")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TestController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('SELLER','CUSTOMER')")
    public ResponseEntity<String> test(HttpServletResponse response){
        return ResponseEntity.ok().body("Hit endpoint test");
    }
}
