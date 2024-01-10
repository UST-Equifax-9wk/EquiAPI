package com.revature.ecommerce.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        String token = cookieUtil.getCookie(request, "jwt");

        if(token != null){
            try{
                SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(token));
                String email = jwtUtil.parseEmail(token);
                String role = jwtUtil.parseRole(token);

                GrantedAuthority auth = new SimpleGrantedAuthority(role);
                List<GrantedAuthority> authorities = Arrays.asList(auth);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
