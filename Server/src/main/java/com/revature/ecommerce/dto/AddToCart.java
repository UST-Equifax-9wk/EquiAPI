package com.revature.ecommerce.dto;

import java.util.Objects;

public class AddToCart {
    Integer productId;
    String customerEmail;
    Double price;


    public AddToCart(Integer productId, Double price) {
        this.productId = productId;
        this.price = price;
    }

    public AddToCart(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddToCart addToCart = (AddToCart) o;
        return Objects.equals(productId, addToCart.productId) && Objects.equals(customerEmail, addToCart.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerEmail);
    }

    @Override
    public String toString() {
        return "AddToCart{" +
                "productId=" + productId +
                ", customerEmail='" + customerEmail +
                '}';
    }
}
