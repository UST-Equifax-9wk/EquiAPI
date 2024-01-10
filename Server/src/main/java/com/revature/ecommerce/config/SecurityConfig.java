package com.revature.ecommerce.config;

import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtCustomerRequestFilter;
import com.revature.ecommerce.util.JwtSellerRequestFilter;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtUtil jwtUtil;
    private CookieUtil cookieUtil;

    @Bean
    public JwtCustomerRequestFilter jwtCustomerRequestFilter(){return new JwtCustomerRequestFilter(jwtUtil, cookieUtil);}
    @Bean
    public JwtSellerRequestFilter jwtSellerRequestFilter(){return new JwtSellerRequestFilter(jwtUtil, cookieUtil);}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())
                ))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .addFilterAfter(jwtCustomerRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtSellerRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
