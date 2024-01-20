package com.revature.ecommerce.services;

import com.revature.ecommerce.dto.ReviewDto;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.entities.Review;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class ReviewService {

    private ReviewRepository reviewRepository;
    private CustomerService customerService;
    private ProductService productService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CustomerService customerService, ProductService productService){
        this.reviewRepository = reviewRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public Review writeReview(String email, ReviewDto reviewDto){
        Customer customer = customerService.findByEmail(email);
        Product product = productService.findById(reviewDto.getProductId());
        Review review = new Review();
        review.setComment(reviewDto.getText());
        review.setRating(reviewDto.getRating());
        review.setCustomer(customer);
        review.setProduct(product);

        return reviewRepository.save(review);
    }

    public Review deleteReview(String email, ReviewDto reviewDto) throws UnableToDeleteItemException{
        Customer customer = customerService.findByEmail(email);
        Set<Review> reviews = customer.getReviews();
        Review deletedReview = new Review();
        for(Review r : reviews){
            if(Objects.equals(r.getReviewsId(), reviewDto.getReviewId())){
                deletedReview = r;
                reviewRepository.delete(reviewRepository.findReviewByReviewId(reviewDto.getReviewId()));
                return deletedReview;
            }
        }
        throw new UnableToDeleteItemException("User did not write this review");
    }

    public Set<Review> findReviewByProductId(Integer productId){
        Product product = productService.findById(productId);
        return product.getReviews();
    }
}
