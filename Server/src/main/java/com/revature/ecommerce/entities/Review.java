package com.revature.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

@Entity(name= "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewsId;

    @Column
    private String comment;

    @Column(name= "rating")
    @Max(value = 5)
    private Double rating;

    @JsonBackReference(value = "customerReviewReference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @JsonBackReference(value = "productReviewReference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    //customer ID Needed

    public Review() {
    }

    public Review(String comment, Double rating, Product product, Customer customer) {
        this.comment = comment;
        this.rating = rating;
        this.product = product;
        this.customer = customer;
    }

    public Review(Integer reviewsId, String comment, Double rating){//}, Product product) {
        this.reviewsId = reviewsId;
        this.comment = comment;
        this.rating = rating;
//        this.product = product;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
