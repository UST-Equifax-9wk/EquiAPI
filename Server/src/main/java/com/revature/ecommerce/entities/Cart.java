package com.revature.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Data

public class Cart {
    @Column(name = "list_of_items")
    List<String> productId;  //maybe use a map here? product id to quantity of product

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer serialNumber;
    @Column
    List<Integer> quantity; //map<String productId, Integer Quantity>
    @Column
    Integer customerId;

    public Cart() {
    }

    public Cart(List<String> productId, Integer customerId) {
        this.productId = productId;
        this.customerId = customerId;
    }

    public List<String> getProductId() {
        return productId;
    }

    public void setProductId(List<String> productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(productId, cart.productId) && Objects.equals(customerId, cart.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productId=" + productId +
                ", customerId=" + customerId +
                '}';
    }
}

