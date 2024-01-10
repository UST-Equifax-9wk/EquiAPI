package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.SellerResponse;

import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.services.SellerService;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    private final SellerService sellerService;
    private final JwtUtil jwtUtil;


    @PostMapping("/seller/sign-up")
    public ResponseEntity<SellerResponse> signUpSeller(@RequestBody Seller seller, HttpServletResponse response){
        Seller newSeller = sellerService.save(seller);
        String jwt = jwtUtil.generateToken(newSeller.getEmail(), newSeller.getRole());
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setSecure(true); // Send over HTTPS only
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(10800);
        response.addCookie(cookie);

        SellerResponse res = new  SellerResponse(
                newSeller.getId(),
                newSeller.getFirstName(),
                newSeller.getLastName(),
                newSeller.getEmail()
        );

        return ResponseEntity.ok(res);
    }
}
