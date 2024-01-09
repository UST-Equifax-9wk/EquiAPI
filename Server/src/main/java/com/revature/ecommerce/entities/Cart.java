package com.revature.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer serialNumber;

    @Column
    Integer quantity;
//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;
//
//    @OneToMany
//    private Set<Product> product;

    public Cart() {
    }

    public Cart(Integer quantity) {
        this.quantity = quantity;
    }

//    public Cart(Integer quantity){//}, Customer customer) {
//        this.quantity = quantity;
////        this.customer = customer;
//    }

    public Cart(Integer serialNumber, Integer quantity){//, Customer customer) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
//        this.customer = customer;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

//    public void setSerialNumber(Integer serialNumber) {
//        this.serialNumber = serialNumber;
//    }

//    public Integer getProductId() {
//        return productId;
//    }

//    public void setProductId(Integer productId) {
//        this.productId = productId;
//    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

//    public Customer getCustomer() {
//        return customer;
//    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(serialNumber, cart.serialNumber)
                && Objects.equals(quantity, cart.quantity);
                //&& Objects.equals(customer, cart.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, quantity)//, customer)
                ;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "serialNumber=" + serialNumber +
                ", quantity=" + quantity +
//                ", customer=" + customer +
                '}';
    }
}

