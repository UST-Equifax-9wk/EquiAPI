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
    Integer quantity;

    @Column
    Integer productId;

    @Column
    Double price;

    @Column
    Double totalPrice;

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    @JsonBackReference(value = "customerReference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

//    @JsonBackReference(value = "orderReference")
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
//    private Order order;

    /**
     * Constructor with all parameters
     * @param cartId
     * @param quantity
     * @param productId
     * @param totalPrice
     * @param customer
     * @param price
     */
    public Cart(Integer cartId, Integer quantity, Integer productId, Double totalPrice,
                Customer customer, Double price) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.customer = customer;
//        this.order = order;
        this.price = price;
    }

    /**
     * No argument constructor
     */
    public Cart() {
    }

    /**
     * Constructor without Order and without cartId
     * @param quantity
     * @param customer
     * @param productId
     * @param totalPrice
     */
    public Cart(Integer quantity, Customer customer, Integer productId, Double totalPrice, Double price) {
        this.quantity = quantity;
        this.customer = customer;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.price = price;
    }


    /**
     * I am unsure if I used this constructor anymore, but since it doesn't hurt...
     * @param quantity
     * @param customer
     */
    public Cart(Integer quantity, Customer customer) {
        this.quantity = quantity;
        this.customer = customer;
    }

    /**
     * Constructor without the order as the order is only available at the time of purchase...Lazy?
     * @param cartId
     * @param quantity
     * @param customer
     * @param productId
     * @param totalPrice
     */
    public Cart(Integer cartId, Integer quantity, Customer customer, Integer productId, Double totalPrice, Double price) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.productId = productId;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.price = price;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId)
                && Objects.equals(quantity, cart.quantity);
        //&& Objects.equals(customer, cart.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, quantity)//, customer)
                ;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "serialNumber=" + cartId +
                ", quantity=" + quantity +
                ", productId=" + productId +
                ", customer=" + customer +
                '}';
    }
}