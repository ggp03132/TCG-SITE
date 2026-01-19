package com.sevensaints.backend.repository;

import com.sevensaints.backend.model.entity.Card;
import com.sevensaints.backend.model.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    
    List<Card> findByCardType(CardType cardType);
    
    List<Card> findByCardNameContaining(String cardName);
    
    boolean existsByCardName(String cardName);
}
