package com.revature.ecommerce.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer serialNumber;
    @Column(name = "product_id")
    Integer productId;
    @Column(name = "product_desc")
    String productDesc;
    @Column
    Double price;
    @Column(name="seller_id")
    Integer sellerId;

    //Customer_id needs to be set here

    public Cart() {
    }

    public Cart(Integer productId, String productDesc, Double price, Integer sellerId) {
        this.productId = productId;
        this.productDesc = productDesc;
        this.price = price;
        this.sellerId = sellerId;
    }

    public Cart(Integer serialNumber, Integer productId, String productDesc, Double price, Integer sellerId) {
        this.serialNumber = serialNumber;
        this.productId = productId;
        this.productDesc = productDesc;
        this.price = price;
        this.sellerId = sellerId;
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(serialNumber, cart.serialNumber) && Objects.equals(productId, cart.productId) && Objects.equals(productDesc, cart.productDesc) && Objects.equals(price, cart.price) && Objects.equals(sellerId, cart.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, productId, productDesc, price, sellerId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "serialNumber=" + serialNumber +
                ", productId=" + productId +
                ", productDesc='" + productDesc + '\'' +
                ", price=" + price +
                ", sellerId=" + sellerId +
                '}';
    }
}
