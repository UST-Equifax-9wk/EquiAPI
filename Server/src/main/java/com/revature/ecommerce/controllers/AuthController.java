package com.revature.ecommerce.controllers;
import com.revature.ecommerce.dto.CustomerLoginDto;
import com.revature.ecommerce.dto.CustomerResponse;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.services.CustomerService;
import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    private final CustomerService customerService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CookieUtil cookieUtil;


    /* SIGN UP FUNCTIONS FOR SELLER & CUSTOMER*/

    @PostMapping("/customer/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
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

    @PostMapping("/customer/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> signInCustomer(@RequestBody CustomerLoginDto customer, HttpServletResponse response) throws UserDoesNotExistException {
        Customer auth = customerService.findByEmail(customer.getEmail());
        System.out.println(auth.getEmail());
        System.out.println(passwordEncoder.matches(customer.getPassword(), auth.getPassword()));

        if(auth.getEmail() == null || !passwordEncoder.matches(customer.getPassword(), auth.getPassword())){
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

    @PostMapping("/customer/logout")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response){

            Cookie cookie = new Cookie("jwt", null);
            cookie.setSecure(true); // Send over HTTPS only
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);

    }

    @GetMapping("/customer/auth")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> checkAuth(HttpServletRequest request, HttpServletResponse response) throws UsernameNotFoundException {
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        Customer auth = customerService.findByEmail(email);

        if(auth == null){
            throw new UsernameNotFoundException("User is not logged in");
        }

        CustomerResponse res = new CustomerResponse(
                auth.getCustomerId(),
                auth.getFirstName(),
                auth.getLastName(),
                auth.getEmail()
        );

        return ResponseEntity.ok(res);
    }





}