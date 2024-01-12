package com.revature.ecommerce.util;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.repositories.CustomerRepository;
import com.revature.ecommerce.repositories.SellerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        String token = cookieUtil.getCookie(request, "jwt");

        if(token != null){

            try {
                SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(token));
            } catch (UserDoesNotExistException e) {
                throw new RuntimeException(e);
            }

        }

        filterChain.doFilter(request, response);
    }
}