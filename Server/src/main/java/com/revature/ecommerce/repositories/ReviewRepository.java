package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM reviews WHERE product_id = ?1")
    Set <Review> findByProductId(Integer productId);

    @Query(nativeQuery = true, value = "SELECT * FROM reviews WHERE review_id = 1?")
    Review findReviewByReviewId(Integer reviewId);
}
