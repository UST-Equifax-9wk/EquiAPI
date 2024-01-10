package com.revature.ecommerce.util;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.repositories.CustomerRepository;
import com.revature.ecommerce.repositories.SellerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;


@Component
public class JwtUtil {

    private CustomerRepository customerRepository;
    private SellerRepository sellerRepository;

    @Autowired
    public JwtUtil(CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Value("${secret.key}")
    private String secret;

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email){
        Date exp = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 7);

        return Jwts.builder()
                .subject(email)
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
                .getSubject();
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
    public Authentication getCustomerAuthentication(String token) throws UserDoesNotExistException {
        String customerEmail = parseEmail(token);
        Customer customer = customerRepository.findByEmail(customerEmail);

        if(customer == null){
           throw new UserDoesNotExistException("User not found!");
        }

        return new UsernamePasswordAuthenticationToken(customer, null, new ArrayList<>());
    }

    /**
     * For Seller authentication filter
     */
    public Authentication getSellerAuthentication(String token) throws UserDoesNotExistException {
        String sellerEmail = parseEmail(token);
        Seller seller = sellerRepository.findByEmail(sellerEmail);

        if(seller == null){
            throw new UserDoesNotExistException("User not found!");
        }

        return new UsernamePasswordAuthenticationToken(seller, null, new ArrayList<>());
    }

}
