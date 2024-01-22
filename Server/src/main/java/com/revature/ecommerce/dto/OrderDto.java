package com.revature.ecommerce.dto;

import java.util.Date;

public class OrderDto {

    private Integer orderId;
    private Date datoOfPurchase;
    private Double totalCost;
    private String [] itemDescription;

    public OrderDto() {
    }

    public OrderDto(Integer orderId, Date datoOfPurchase, Double totalCost, String [] itemDescription) {
        this.orderId = orderId;
        this.datoOfPurchase = datoOfPurchase;
        this.totalCost = totalCost;
        this.itemDescription = itemDescription;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getDatoOfPurchase() {
        return datoOfPurchase;
    }

    public void setDatoOfPurchase(Date datoOfPurchase) {
        this.datoOfPurchase = datoOfPurchase;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String [] getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String [] itemDescription) {
        this.itemDescription = itemDescription;
    }
}
