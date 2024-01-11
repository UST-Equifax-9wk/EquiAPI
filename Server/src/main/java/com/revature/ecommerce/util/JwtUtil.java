package com.revature.ecommerce.util;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;

import com.revature.ecommerce.services.CustomerService;
import com.revature.ecommerce.services.SellerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;

import javax.crypto.SecretKey;
import java.util.*;


@Component
public class JwtUtil {

  private final CustomerService customerService;
  private final SellerService sellerService;
  private final String secret;

  @Autowired
  public JwtUtil(CustomerService customerService, SellerService sellerService, @Value("${secret.key}") String secret){
       this.customerService = customerService;
       this.sellerService = sellerService;
       this.secret = secret;
  }

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role){
        Date exp = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 7);

        return Jwts.builder()
                .subject(email+","+role)
                .signWith(getKey())
                .issuedAt(new Date())
                .expiration(exp)
                .compact();
    }

    public String parseEmail(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject().split(",")[0];



    }

    public String parseRole(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject()
                .split(",")[1];


    }


    public boolean isTokenExpired(String token){
        return !Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }


/**
 * For customer authentication filter
 */
    public Authentication getAuthentication(String token) throws UserDoesNotExistException {
        String role = parseRole(token);
        String email = parseEmail(token);


        GrantedAuthority customerAuthority = new SimpleGrantedAuthority(role);
        Collection<GrantedAuthority> authorities = Collections.singleton(customerAuthority);


        if(role.equals("CUSTOMER")){
            Customer customer = customerService.findByEmail(email);
            return new UsernamePasswordAuthenticationToken(customer.getEmail(), null, authorities);
        } else {
            Seller seller = sellerService.findByEmail(email);
            UsernamePasswordAuthenticationToken tokened = new UsernamePasswordAuthenticationToken(seller.getEmail(), seller.getPassword(), authorities);
            System.out.println(tokened);
            return tokened;
        }


    }
}

