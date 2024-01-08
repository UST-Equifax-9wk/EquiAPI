package com.revature.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

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
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Cart() {
    }

    public Cart(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(Integer productId, Integer quantity, Customer customer) {
        this.productId = productId;
        this.quantity = quantity;
        this.customer = customer;
    }

    public Cart(Integer serialNumber, Integer productId, Integer quantity, Customer customer) {
        this.serialNumber = serialNumber;
        this.productId = productId;
        this.quantity = quantity;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(serialNumber, cart.serialNumber) && Objects.equals(productId, cart.productId) && Objects.equals(quantity, cart.quantity) && Objects.equals(customer, cart.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, productId, quantity, customer);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "serialNumber=" + serialNumber +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", user=" + customer +
                '}';
    }
}

