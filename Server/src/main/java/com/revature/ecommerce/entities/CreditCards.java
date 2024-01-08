package com.revature.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity(name = "credit_cards")
public class CreditCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer product;

    @Column(name= "card_number")
    @Max(16)
    @Min(16)
    private Long cardNumber;

    @Column(name = "cvv")
    @Min(3)
    @Max(4)
    private Integer cvv;

    @Column(name = "name_on_card")
    private String nameOnCard;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
