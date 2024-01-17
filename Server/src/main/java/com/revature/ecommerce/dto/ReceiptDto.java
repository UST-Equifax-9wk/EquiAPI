package com.revature.ecommerce.dto;

import com.revature.ecommerce.entities.Address;

import java.util.Date;

public class ReceiptDto {

    private Integer orderId;
    private Date datoOfPurchase;
    private String paymentMethod;
    private Double totalCost;
    private String itemDescription;

    private Address shippingAddress;

    private Address billingAddress;

    public ReceiptDto(Integer orderId, Date datoOfPurchase, String paymentMethod, Double totalCost, String itemDescription) {
        this.orderId = orderId;
        this.datoOfPurchase = datoOfPurchase;
        this.paymentMethod = paymentMethod;
        this.totalCost = totalCost;
        this.itemDescription = itemDescription;
    }
}
