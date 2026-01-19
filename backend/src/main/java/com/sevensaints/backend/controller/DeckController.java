package com.sevensaints.backend.controller;

import com.sevensaints.backend.dto.DeckDTO;
import com.sevensaints.backend.model.enums.TierRank;
import com.sevensaints.backend.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
@RequiredArgsConstructor
public class DeckController {
    
    private final DeckService deckService;
    
    @GetMapping
    public ResponseEntity<List<DeckDTO>> getAllDecks(
            @RequestParam(required = false) TierRank tier,
            @RequestParam(required = false) String sortBy
    ) {
        List<DeckDTO> decks;
        
        if (tier != null) {
            decks = deckService.getDecksByTier(tier);
        } else if ("popularity".equals(sortBy)) {
            decks = deckService.getTopDecksByPopularity();
        } else if ("winrate".equals(sortBy)) {
            decks = deckService.getTopDecksByWinRate();
        } else {
            decks = deckService.getAllDecks();
        }
        
        return ResponseEntity.ok(decks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DeckDTO> getDeckById(@PathVariable Long id) {
        DeckDTO deck = deckService.getDeckById(id);
        return ResponseEntity.ok(deck);
    }
    
    @PostMapping
    public ResponseEntity<DeckDTO> createDeck(@RequestBody @NonNull DeckDTO deckDTO) {
        DeckDTO created = deckService.createDeck(deckDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DeckDTO> updateDeck(
            @PathVariable @NonNull Long id,
            @RequestBody @NonNull DeckDTO deckDTO
    ) {
        DeckDTO updated = deckService.updateDeck(id, deckDTO);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable @NonNull Long id) {
        deckService.deleteDeck(id);
        return ResponseEntity.noContent().build();
    }
}
