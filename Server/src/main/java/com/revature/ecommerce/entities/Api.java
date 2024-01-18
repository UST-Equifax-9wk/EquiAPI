package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID apiId;

    private Integer productId;

    private Integer orderId;

    @JsonBackReference(value = "apiReference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public Api() {
    }

    public Api(Integer productId, Customer customer, Integer orderId) {
        this.productId = productId;
        this.customer = customer;
        this.orderId = orderId;
    }

    public Api(UUID apiId, Integer productId, Customer customer, Integer orderId) {
        this.apiId = apiId;
        this.productId = productId;
        this.customer = customer;
        this.orderId = orderId;
    }

    public UUID getApiId() {
        return apiId;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Api api = (Api) o;
        return Objects.equals(apiId, api.apiId) && Objects.equals(productId, api.productId) && Objects.equals(customer, api.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId, productId, customer);
    }

    @Override
    public String toString() {
        return "Api{" +
                "apiNumber=" + apiId +
                ", productId=" + productId +
                ", customer=" + customer +
                '}';
    }
}
