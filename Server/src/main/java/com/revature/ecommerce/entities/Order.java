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

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "order_status")
    private Boolean orderStatus;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "shipment_date")
    private Date shipmentDate;

    @Column(name = "ordered_items")
    private String orderedItems;

    @JsonBackReference(value = "orderReference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public Order() {
    }


    public Order(Date dateOfPurchase, String paymentMethod, Boolean orderStatus, Double totalCost,
                 Date shipmentDate, String orderedItems) {
        this.dateOfPurchase = dateOfPurchase;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.shipmentDate = shipmentDate;
        this.orderedItems = orderedItems;
    }

    public Order(Integer orderId, Date dateOfPurchase, String paymentMethod, Boolean orderStatus, Double totalCost,
                 Date shipmentDate, String orderedItems) {
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.shipmentDate = shipmentDate;
        this.orderedItems = orderedItems;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(String orderedItems) {
        this.orderedItems = orderedItems;
    }

//    public Set<Cart> getCart() {
//        return cart;
//    }
//
//    public void setCart(Set<Cart> cart) {
//        this.cart = cart;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(dateOfPurchase, order.dateOfPurchase) && Objects.equals(paymentMethod, order.paymentMethod) && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(totalCost, order.totalCost) && Objects.equals(shipmentDate, order.shipmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dateOfPurchase, paymentMethod, orderStatus, totalCost, shipmentDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", dateOfPurchase=" + dateOfPurchase +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderStatus=" + orderStatus +
                ", totalCost=" + totalCost +
                ", shipmentDate=" + shipmentDate +
                '}';
    }
}
