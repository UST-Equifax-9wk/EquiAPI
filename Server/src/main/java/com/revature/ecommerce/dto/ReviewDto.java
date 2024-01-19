package com.revature.ecommerce.dto;

import java.util.Objects;

public class ReviewDto {
    private Integer reviewId;
    private Integer productId;
    private String text;
    private Double rating;

    public ReviewDto(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public ReviewDto(Integer productId, String text, Double rating) {
        this.productId = productId;
        this.text = text;
        this.rating = rating;
    }

//    public ReviewDto(Integer reviewId, Integer productId, String text, Double rating) {
//        this.reviewId = reviewId;
//        this.productId = productId;
//        this.text = text;
//        this.rating = rating;
//    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDto reviewDto = (ReviewDto) o;
        return Objects.equals(productId, reviewDto.productId) && Objects.equals(text, reviewDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, text);
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "productId=" + productId +
                ", text='" + text + '\'' +
                '}';
    }
}
