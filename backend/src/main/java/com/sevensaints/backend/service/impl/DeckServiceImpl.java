package com.sevensaints.backend.service.impl;

import com.sevensaints.backend.dto.DeckCardDTO;
import com.sevensaints.backend.dto.DeckDTO;
import com.sevensaints.backend.model.entity.Card;
import com.sevensaints.backend.model.entity.Deck;
import com.sevensaints.backend.model.entity.DeckCard;
import com.sevensaints.backend.model.enums.TierRank;
import com.sevensaints.backend.repository.CardRepository;
import com.sevensaints.backend.repository.DeckRepository;
import com.sevensaints.backend.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeckServiceImpl implements DeckService {
    
    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    
    @Override
    public List<DeckDTO> getAllDecks() {
        return deckRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public DeckDTO getDeckById(Long id) {
        Deck deck = deckRepository.findByIdWithCards(id);
        if (deck == null) {
            throw new RuntimeException("Deck not found with id: " + id);
        }
        return convertToDTO(deck);
    }
    
    @Override
    public List<DeckDTO> getDecksByTier(TierRank tierRank) {
        return deckRepository.findByTierRank(tierRank).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DeckDTO> getTopDecksByPopularity() {
        return deckRepository.findAllByPopularity().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DeckDTO> getTopDecksByWinRate() {
        return deckRepository.findAllByWinRate().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    @SuppressWarnings("null") // cardDTO.getCardId() is guaranteed to be non-null in valid DTOs
    public DeckDTO createDeck(@NonNull DeckDTO dto) {
        Deck deck = new Deck();
        deck.setDeckName(dto.getDeckName());
        deck.setDescription(dto.getDescription());
        deck.setAuthor(dto.getAuthor());
        deck.setTierRank(dto.getTierRank());
        deck.setWins(dto.getWins() != null ? dto.getWins() : 0);
        deck.setLosses(dto.getLosses() != null ? dto.getLosses() : 0);
        deck.setPopularityScore(dto.getPopularityScore() != null ? dto.getPopularityScore() : 0);
        
        // Add cards to deck
        if (dto.getCards() != null && !dto.getCards().isEmpty()) {
            for (DeckCardDTO cardDTO : dto.getCards()) {
                Card card = cardRepository.findById(cardDTO.getCardId())
                        .orElseThrow(() -> new RuntimeException("Card not found with id: " + cardDTO.getCardId()));
                
                DeckCard deckCard = new DeckCard();
                deckCard.setDeck(deck);
                deckCard.setCard(card);
                deckCard.setQuantity(cardDTO.getQuantity());
                deck.getDeckCards().add(deckCard);
            }
        }
        
        deck = deckRepository.save(deck);
        return convertToDTO(deck);
    }
    
    @Override
    @Transactional
    @SuppressWarnings("null") // cardDTO.getCardId() is guaranteed to be non-null in valid DTOs
    public DeckDTO updateDeck(@NonNull Long id, @NonNull DeckDTO dto) {
        Deck deck = deckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deck not found with id: " + id));
        
        deck.setDeckName(dto.getDeckName());
        deck.setDescription(dto.getDescription());
        deck.setAuthor(dto.getAuthor());
        deck.setTierRank(dto.getTierRank());
        deck.setWins(dto.getWins());
        deck.setLosses(dto.getLosses());
        deck.setPopularityScore(dto.getPopularityScore());
        
        // Update cards
        deck.getDeckCards().clear();
        if (dto.getCards() != null && !dto.getCards().isEmpty()) {
            for (DeckCardDTO cardDTO : dto.getCards()) {
                Card card = cardRepository.findById(cardDTO.getCardId())
                        .orElseThrow(() -> new RuntimeException("Card not found with id: " + cardDTO.getCardId()));
                
                DeckCard deckCard = new DeckCard();
                deckCard.setDeck(deck);
                deckCard.setCard(card);
                deckCard.setQuantity(cardDTO.getQuantity());
                deck.getDeckCards().add(deckCard);
            }
        }
        
        deck = deckRepository.save(deck);
        return convertToDTO(deck);
    }
    
    @Override
    @Transactional
    @SuppressWarnings("null") // deck is guaranteed to be non-null by orElseThrow
    public void deleteDeck(@NonNull Long id) {
        Deck deck = deckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deck not found with id: " + id));
        deckRepository.delete(deck);
    }
    
    private DeckDTO convertToDTO(Deck entity) {
        DeckDTO dto = new DeckDTO();
        dto.setId(entity.getId());
        dto.setDeckName(entity.getDeckName());
        dto.setDescription(entity.getDescription());
        dto.setAuthor(entity.getAuthor());
        dto.setTierRank(entity.getTierRank());
        dto.setWins(entity.getWins());
        dto.setLosses(entity.getLosses());
        dto.setPopularityScore(entity.getPopularityScore());
        
        if (entity.getDeckCards() != null && !entity.getDeckCards().isEmpty()) {
            List<DeckCardDTO> cardDTOs = entity.getDeckCards().stream()
                    .map(this::convertDeckCardToDTO)
                    .collect(Collectors.toList());
            dto.setCards(cardDTOs);
        }
        
        return dto;
    }
    
    private DeckCardDTO convertDeckCardToDTO(DeckCard entity) {
        DeckCardDTO dto = new DeckCardDTO();
        dto.setCardId(entity.getCard().getId());
        dto.setCardName(entity.getCard().getCardName());
        dto.setCardType(entity.getCard().getCardType().name());
        dto.setQuantity(entity.getQuantity());
        dto.setImageUrl(entity.getCard().getImageUrl());
        return dto;
    }
}
