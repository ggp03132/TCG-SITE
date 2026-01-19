package com.sevensaints.backend.repository;

import com.sevensaints.backend.model.entity.DeckCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
    
    List<DeckCard> findByDeckId(Long deckId);
    
    void deleteByDeckId(Long deckId);
}
