package com.revature.ecommerce.dto;

import java.util.Date;

public class ReceiptDto {

    private Integer orderId;
    private Date datoOfPurchase;
    private String paymentMethod;
    private Double totalCost;
    private String itemDescription;

    public ReceiptDto(Integer orderId, Date datoOfPurchase, String paymentMethod, Double totalCost, String itemDescription) {
        this.orderId = orderId;
        this.datoOfPurchase = datoOfPurchase;
        this.paymentMethod = paymentMethod;
        this.totalCost = totalCost;
        this.itemDescription = itemDescription;
    }
}
