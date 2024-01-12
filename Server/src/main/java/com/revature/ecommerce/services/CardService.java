package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.CreditCards;
import com.revature.ecommerce.repositories.CreditCardsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CardService {

    private final CreditCardsRepository cardRepository;

    @Autowired
    public CardService (CreditCardsRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public CreditCards addCard(CreditCards card){
        return cardRepository.save(card);
    }

}
