package com.revature.ecommerce.controllers;
import com.revature.ecommerce.dto.CustomerResponse;
import com.revature.ecommerce.dto.SellerResponse;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Seller;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.services.CustomerService;
import com.revature.ecommerce.services.SellerService;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    private final SellerService sellerService;
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    /* SIGN UP FUNCTIONS FOR SELLER & CUSTOMER*/

    @PostMapping("/seller/sign-up")
    @PreAuthorize("permitAll()")
    public ResponseEntity<SellerResponse> signUpSeller(@RequestBody Seller seller, HttpServletResponse response){
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
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


    @PostMapping("/customer/sign-up")
    public ResponseEntity<CustomerResponse> signUpCustomer(@RequestBody Customer customer, HttpServletResponse response) throws UserAlreadyExistsException {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer newCustomer = customerService.addCustomer(customer);
        String jwt = jwtUtil.generateToken(newCustomer.getEmail(), newCustomer.getRole());
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setSecure(true); // Send over HTTPS only
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(10800);
        response.addCookie(cookie);

        CustomerResponse res = new CustomerResponse(
                newCustomer.getCustomerId(),
                newCustomer.getFirstName(),
                newCustomer.getLastName(),
                newCustomer.getEmail()
        );

        return ResponseEntity.ok(res);
    }

    /* SIGN IN FUNCTIONS FOR SELLER & CUSTOMER*/

    @PostMapping("/seller/sign-in")
    public ResponseEntity<SellerResponse> signInSeller(@RequestBody Seller seller, HttpServletResponse response) throws UserDoesNotExistException {
        Seller auth = sellerService.findByEmail(seller.getEmail());
        

        if(auth == null || !passwordEncoder.matches(seller.getPassword(), auth.getPassword())){
            throw new UserDoesNotExistException("Username or password incorrect");
        }

        String jwt = jwtUtil.generateToken(auth.getEmail(), auth.getRole());
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setSecure(true); // Send over HTTPS only
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(10800);
        response.addCookie(cookie);

        SellerResponse res = new  SellerResponse(
                auth.getId(),
                auth.getFirstName(),
                auth.getLastName(),
                auth.getEmail()
        );

        return ResponseEntity.ok(res);
    }


    @PostMapping("/customer/sign-in")
    public ResponseEntity<CustomerResponse> signInCustomer(@RequestBody Customer customer, HttpServletResponse response) throws UserDoesNotExistException {
        Customer auth = customerService.findByEmail(customer.getEmail());

        if(auth == null || !passwordEncoder.matches(customer.getPassword(), auth.getPassword())){
            throw new UserDoesNotExistException("Username or password incorrect");
        }

        String jwt = jwtUtil.generateToken(auth.getEmail(), auth.getRole());
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setSecure(true); // Send over HTTPS only
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(10800);
        response.addCookie(cookie);

        CustomerResponse res = new CustomerResponse(
                auth.getCustomerId(),
                auth.getFirstName(),
                auth.getLastName(),
                auth.getEmail()
        );

        return ResponseEntity.ok(res);
    }


}
