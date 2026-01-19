package com.sevensaints.backend.service;

import com.sevensaints.backend.dto.DeckDTO;
import com.sevensaints.backend.model.enums.TierRank;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DeckService {
    
    List<DeckDTO> getAllDecks();
    
    DeckDTO getDeckById(Long id);
    
    List<DeckDTO> getDecksByTier(TierRank tierRank);
    
    List<DeckDTO> getTopDecksByPopularity();
    
    List<DeckDTO> getTopDecksByWinRate();
    
    DeckDTO createDeck(@NonNull DeckDTO deckDTO);
    
    DeckDTO updateDeck(@NonNull Long id, @NonNull DeckDTO deckDTO);
    
    void deleteDeck(@NonNull Long id);
}
