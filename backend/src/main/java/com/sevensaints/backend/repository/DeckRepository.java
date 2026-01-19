package com.sevensaints.backend.repository;

import com.sevensaints.backend.model.entity.Deck;
import com.sevensaints.backend.model.enums.TierRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    
    List<Deck> findByTierRank(TierRank tierRank);
    
    List<Deck> findByAuthor(String author);
    
    @Query("SELECT d FROM Deck d ORDER BY d.popularityScore DESC")
    List<Deck> findAllByPopularity();
    
    @Query("SELECT d FROM Deck d ORDER BY (d.wins * 1.0 / (d.wins + d.losses)) DESC")
    List<Deck> findAllByWinRate();
    
    @Query("SELECT d FROM Deck d JOIN FETCH d.deckCards dc JOIN FETCH dc.card WHERE d.id = :deckId")
    Deck findByIdWithCards(Long deckId);
}
