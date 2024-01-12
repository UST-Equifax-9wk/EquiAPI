package com.revature.ecommerce.dto;

import java.util.Objects;

public class AddToCart {
    Integer productId;
    String customerEmail;
    Integer quantity;
    Double price;


    public AddToCart(Integer productId, String customerEmail, Integer quantity, Double price) {
        this.productId = productId;
        this.customerEmail = customerEmail;
        this.quantity = quantity;
        this.price = price;
    }

    public AddToCart(Integer productId, Integer quantity, Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        return Objects.equals(productId, addToCart.productId) && Objects.equals(customerEmail, addToCart.customerEmail) && Objects.equals(quantity, addToCart.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerEmail, quantity);
    }

    @Override
    public String toString() {
        return "AddToCart{" +
                "productId=" + productId +
                ", customerEmail='" + customerEmail + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
