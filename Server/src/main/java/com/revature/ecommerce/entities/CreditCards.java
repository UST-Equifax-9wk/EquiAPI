package com.revature.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity(name = "credit_cards")
public class CreditCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer cardId;

    @Column(name= "card_number", length = 16)
//    @Max(16)
//    @Min(16)
    private String cardNumber;

    @Column(name = "cvv")
    @Min(3)
    @Max(4)
    private Integer cvv;

    @Column(name = "name_on_card")
    private String nameOnCard;

    @Column(name = "expiry_date")
    private String expiryDate;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;


    public CreditCards() {
    }

    public CreditCards(String cardNumber, Integer cvv, String nameOnCard, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
    }

    public CreditCards(Integer cardId, String cardNumber, Integer cvv, String nameOnCard, String expiryDate) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCards that = (CreditCards) o;
        return Objects.equals(cardId, that.cardId) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cvv, that.cvv) && Objects.equals(nameOnCard, that.nameOnCard) && Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardNumber, cvv, nameOnCard, expiryDate);
    }

    @Override
    public String toString() {
        return "CreditCards{" +
                "cardId=" + cardId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv=" + cvv +
                ", nameOnCard='" + nameOnCard + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
