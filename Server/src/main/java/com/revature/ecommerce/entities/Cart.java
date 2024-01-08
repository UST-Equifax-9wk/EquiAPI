package com.revature.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Data

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer serialNumber;
    @Column
    Integer productId;
    @Column
    Integer quantity;
    @ManyToOne
    private User user;

    public Cart() {
    }

    public Cart(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(Integer productId, Integer quantity, User user) {
        this.productId = productId;
        this.quantity = quantity;
        this.user = user;
    }

    public Cart(Integer serialNumber, Integer productId, Integer quantity, User user) {
        this.serialNumber = serialNumber;
        this.productId = productId;
        this.quantity = quantity;
        this.user = user;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

//    public void setSerialNumber(Integer serialNumber) {
//        this.serialNumber = serialNumber;
//    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(serialNumber, cart.serialNumber) && Objects.equals(productId, cart.productId) && Objects.equals(quantity, cart.quantity) && Objects.equals(user, cart.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, productId, quantity, user);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "serialNumber=" + serialNumber +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", user=" + user +
                '}';
    }
}

