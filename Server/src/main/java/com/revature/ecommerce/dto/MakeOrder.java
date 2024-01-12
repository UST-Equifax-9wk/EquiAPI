package com.revature.ecommerce.dto;

import com.revature.ecommerce.entities.Address;
import com.revature.ecommerce.entities.CreditCards;

import java.util.Objects;

public class MakeOrder {
    private Address billingAddress;
    private Address shippingAddress;
    private CreditCards creditCard;

    public MakeOrder(Address billingAddress, Address shippingAddress, CreditCards creditCard) {
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.creditCard = creditCard;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public CreditCards getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCards creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MakeOrder makeOrder = (MakeOrder) o;
        return Objects.equals(billingAddress, makeOrder.billingAddress) && Objects.equals(shippingAddress, makeOrder.shippingAddress) && Objects.equals(creditCard, makeOrder.creditCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billingAddress, shippingAddress, creditCard);
    }

    @Override
    public String toString() {
        return "MakeOrder{" +
                "billingAddress=" + billingAddress +
                ", shippingAddress=" + shippingAddress +
                ", creditCard=" + creditCard +
                '}';
    }
}
