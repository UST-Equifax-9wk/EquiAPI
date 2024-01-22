package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;

    @Column(name = "order_status")
    private Boolean orderStatus;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "ordered_items")
    private String orderedItems;

    @JsonBackReference(value = "orderReference")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public Order() {
    }

    /**
     *
     * @param dateOfPurchase
     * @param orderStatus
     * @param totalCost
     * @param orderedItems
     */

    public Order(Date dateOfPurchase, Boolean orderStatus, Double totalCost, String orderedItems) {
        this.dateOfPurchase = dateOfPurchase;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.orderedItems = orderedItems;
    }

    /**
     *
     * @param orderId
     * @param dateOfPurchase
     * @param orderStatus
     * @param totalCost
     * @param orderedItems
     */

    public Order(Integer orderId, Date dateOfPurchase, Boolean orderStatus, Double totalCost, String orderedItems) {
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.orderedItems = orderedItems;
    }

    public Order(Integer orderId, Date dateOfPurchase, Boolean orderStatus, Double totalCost, String orderedItems, Customer customer) {
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.orderedItems = orderedItems;
        this.customer = customer;
    }

    public Order(Date dateOfPurchase, Boolean orderStatus, Double totalCost, String orderedItems, Customer customer) {
        this.dateOfPurchase = dateOfPurchase;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.orderedItems = orderedItems;
        this.customer = customer;
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

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(String orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(dateOfPurchase, order.dateOfPurchase) && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(totalCost, order.totalCost) && Objects.equals(orderedItems, order.orderedItems) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dateOfPurchase, orderStatus, totalCost, orderedItems, customer);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", dateOfPurchase=" + dateOfPurchase +
                ", orderStatus=" + orderStatus +
                ", totalCost=" + totalCost +
                ", orderedItems='" + orderedItems + '\'' +
                ", customer=" + customer +
                '}';
    }
}
