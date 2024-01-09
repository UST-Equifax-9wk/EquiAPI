package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardsRepository extends JpaRepository<CreditCards, Integer> {
}
