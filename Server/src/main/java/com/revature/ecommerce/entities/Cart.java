package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cartId;

    @Column
    Integer productId;

    @Column
    Double price;

    @Column
    String productName;


    @JsonBackReference(value = "cartReference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;


    /**
     * Constructor with all parameters
     * @param cartId
     * @param productId
     * @param customer
     * @param price
     */
    public Cart(Integer cartId, Integer productId, Customer customer, Double price, String productName) {
        this.cartId = cartId;
        this.productId = productId;
        this.customer = customer;
        this.price = price;
        this.productName = productName;
    }

    /**
     * No argument constructor
     */
    public Cart() {
    }

    /**
     *
     * @param customer
     * @param productId
     * @param price
     */
    public Cart(Customer customer, Integer productId, Double price, String productName) {
        this.customer = customer;
        this.productId = productId;
        this.price = price;
        this.productName = productName;
    }

    /**
     * @param cartId
     * @param customer
     * @param productId
     * @param price
     */
    public Cart(Integer cartId, Customer customer, Integer productId, Double price) {
        this.cartId = cartId;
        this.productId = productId;
        this.customer = customer;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getCartId() {
        return cartId;
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
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId) && Objects.equals(productId, cart.productId) && Objects.equals(price, cart.price) && Objects.equals(customer, cart.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId, price, customer);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}