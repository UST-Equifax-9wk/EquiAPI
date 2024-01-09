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
    @ManyToOne
    private Customer customer;

    @OneToOne
    private Product product;

    public Cart() {
    }

    public Cart(Integer quantity, Customer customer, Product product) {
        this.quantity = quantity;
        this.customer = customer;
        this.product = product;
    }

//    public Cart(Integer quantity, Customer customer) {
//        this.quantity = quantity;
////        this.customer = customer;
//    }

    public Cart(Integer serialNumber, Integer quantity, Customer customer, Product product) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.product = product;
        this.customer = customer;
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



    public Set<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(Set<Integer> quantity) {
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

