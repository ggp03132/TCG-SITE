package com.sevensaints.backend.controller;

import com.sevensaints.backend.dto.CharacterCardDTO;
import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import com.sevensaints.backend.service.CharacterCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards/characters")
@RequiredArgsConstructor
public class CharacterCardController {
    
    private final CharacterCardService characterCardService;
    
    @GetMapping
    public ResponseEntity<List<CharacterCardDTO>> getAllCharacterCards(
            @RequestParam(required = false) ElementType element,
            @RequestParam(required = false) WeaponType weapon
    ) {
        List<CharacterCardDTO> cards = characterCardService.getCharacterCardsByFilters(element, weapon);
        return ResponseEntity.ok(cards);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CharacterCardDTO> getCharacterCardById(@PathVariable @NonNull Long id) {
        CharacterCardDTO card = characterCardService.getCharacterCardById(id);
        return ResponseEntity.ok(card);
    }
    
    @PostMapping
    public ResponseEntity<CharacterCardDTO> createCharacterCard(@RequestBody CharacterCardDTO characterCardDTO) {
        CharacterCardDTO created = characterCardService.createCharacterCard(characterCardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CharacterCardDTO> updateCharacterCard(
            @PathVariable @NonNull Long id,
            @RequestBody @NonNull CharacterCardDTO characterCardDTO
    ) {
        CharacterCardDTO updated = characterCardService.updateCharacterCard(id, characterCardDTO);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacterCard(@PathVariable @NonNull Long id) {
        characterCardService.deleteCharacterCard(id);
        return ResponseEntity.noContent().build();
    }
}
