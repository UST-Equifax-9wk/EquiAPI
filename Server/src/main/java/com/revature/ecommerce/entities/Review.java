package com.revature.ecommerce.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

@Entity(name= "addresses")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Integer reviewsId;

    private String comment;

    @Column(name= "rating")
    @Max(value = 5)
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {
    }

    public Review(String comment, Double rating, Product product) {
        this.comment = comment;
        this.rating = rating;
        this.product = product;
    }

    public Review(Integer reviewsId, String comment, Double rating, Product product) {
        this.reviewsId = reviewsId;
        this.comment = comment;
        this.rating = rating;
        this.product = product;
    }


    public Integer getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(Integer reviewsId) {
        this.reviewsId = reviewsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
