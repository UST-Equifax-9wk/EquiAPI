package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardsRepository extends JpaRepository<CreditCards, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM credit_cards WHERE card_number = ?1")
    CreditCards findByCardNumber(String cardNumber);
}
