package com.revature.ecommerce.controllers;

import com.revature.ecommerce.dto.ReviewDto;
import com.revature.ecommerce.entities.Review;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.services.ReviewService;
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
public class ReviewController {
    private final ReviewService reviewService;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Autowired
    public ReviewController(ReviewService reviewService, JwtUtil jwtUtil, CookieUtil cookieUtil){
        this.reviewService = reviewService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping(path = "/customers/write-review")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Review writeReview(@RequestBody ReviewDto reviewDto, HttpServletResponse response, HttpServletRequest request){
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return reviewService.writeReview(email, reviewDto);
    }
    @GetMapping(path = "/customers/read-review/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Review> readProductReview(@PathVariable Integer productId, HttpServletResponse response, HttpServletRequest request){
        return reviewService.findReviewByProductId(productId);
    }

    @DeleteMapping(path = "/customers/delete-review")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Review deleteReview(@RequestBody ReviewDto reviewDto, HttpServletResponse response, HttpServletRequest request)
    throws UnableToDeleteItemException {
        String email = jwtUtil.parseEmail(cookieUtil.getCookie(request, "jwt"));
        return reviewService.deleteReview(email,reviewDto);
    }

}
