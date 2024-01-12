package com.revature.ecommerce.util;

import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.repositories.CustomerRepository;
import com.revature.ecommerce.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;
    private SellerRepository sellerRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Customer customer = customerRepository.findByEmail(email);
        if(customer != null){
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            return new User(
                    customer.getEmail(),
                    customer.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("CUSTOMER"))
            );
        }

        Seller seller = sellerRepository.findByEmail(email);
        if(seller != null){
            return new User(
                    seller.getEmail(),
                    seller.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("SELLER"))
            );
        }


        throw new UsernameNotFoundException("User not found");
    }

}
