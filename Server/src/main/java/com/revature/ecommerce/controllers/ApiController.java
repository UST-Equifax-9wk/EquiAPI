package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Api;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.services.ApiService;
import com.revature.ecommerce.util.CookieUtil;
import com.revature.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController

public class ApiController {
    private final ApiService apiService;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    @Autowired
    public ApiController(ApiService apiService, JwtUtil jwtUtil, CookieUtil cookieUtil){
        this.apiService = apiService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @GetMapping(path = "/customer/view-apis")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Set<Api> viewApis(HttpServletResponse response, HttpServletRequest request){
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return apiService.findAllCustomerApi(email);
    }

    @DeleteMapping(path = "/customer/delete-api/{apiId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Api DeleteApi(@PathVariable UUID apiId, HttpServletResponse response, HttpServletRequest request)
    throws UnableToDeleteItemException {
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return apiService.deleteApi(email, apiId);
    }

}
