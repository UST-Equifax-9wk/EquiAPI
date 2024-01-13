package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.CreditCards;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.services.CardService;
import com.revature.ecommerce.services.CustomerService;
import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CardController {
    private final CardService cardService;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Autowired
    public CardController(CardService cardService, JwtUtil jwtUtil, CookieUtil cookieUtil){
        this.cardService = cardService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping(path = "/card/add-card")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public CreditCards addCard(@RequestBody CreditCards card, HttpServletResponse response,
                               HttpServletRequest request) throws UnableToAddItemException {
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return cardService.addCard(email, card);
    }

    @PostMapping(path = "/card/delete-card")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public CreditCards deleteCard(@RequestBody CreditCards card, HttpServletResponse response,
                               HttpServletRequest request) throws UnableToDeleteItemException {
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return cardService.deleteCard(email, card);
    }

    @GetMapping(path = "/card/view-card")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public Set<CreditCards> viewAllCustomerCards(HttpServletRequest request){
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return cardService.viewCards(email);
    }
}
