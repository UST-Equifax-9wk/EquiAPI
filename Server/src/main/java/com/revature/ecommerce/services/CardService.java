package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.CreditCards;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.exceptions.UnableToDeleteItemException;
import com.revature.ecommerce.repositories.CreditCardsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Transactional(Transactional.TxType.REQUIRED)
public class CardService {

    private final CreditCardsRepository cardRepository;
    private CustomerService customerService;

    @Autowired
    public CardService (CreditCardsRepository cardRepository, CustomerService customerService){
        this.cardRepository = cardRepository;
        this.customerService = customerService;
    }

    public CreditCards addCard(String email, CreditCards card)
    throws UnableToAddItemException {
        Customer customer = customerService.findByEmail(email);
        Set<CreditCards> cards = customer.getCards();
        for(CreditCards c : cards){
            if(c.getCardNumber().equals(card.getCardNumber())){
                throw new UnableToAddItemException("Card already exists");
            }
        }
        card.setCustomer(customer);
        return cardRepository.save(card);
    }

    public Set<CreditCards> viewCards(String email){
        Customer customer = customerService.findByEmail(email);
        return customer.getCards();
    }

    public CreditCards deleteCard (String email, CreditCards card)
    throws UnableToDeleteItemException {
        Customer customer = customerService.findByEmail(email);
        Set<CreditCards> cards = customer.getCards();
        CreditCards deletedCard = null;
        for(CreditCards c : cards){
            if(c.getCardNumber().equals(card.getCardNumber())){
                deletedCard = c;
            }
        }
        if(deletedCard!=null) {
            cardRepository.delete(cardRepository.getReferenceById(deletedCard.getCardId()));
        }
        return deletedCard;
    }

}
