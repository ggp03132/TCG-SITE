package com.sevensaints.backend.controller;

import com.sevensaints.backend.dto.ActionCardDTO;
import com.sevensaints.backend.model.enums.ActionType;
import com.sevensaints.backend.service.ActionCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards/actions")
@RequiredArgsConstructor
public class ActionCardController {
    
    private final ActionCardService actionCardService;
    
    @GetMapping
    public ResponseEntity<List<ActionCardDTO>> getAllActionCards(
            @RequestParam(required = false) ActionType type
    ) {
        List<ActionCardDTO> cards;
        if (type != null) {
            cards = actionCardService.getActionCardsByType(type);
        } else {
            cards = actionCardService.getAllActionCards();
        }
        return ResponseEntity.ok(cards);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ActionCardDTO> getActionCardById(@PathVariable @NonNull Long id) {
        ActionCardDTO card = actionCardService.getActionCardById(id);
        return ResponseEntity.ok(card);
    }
    
    @PostMapping
    public ResponseEntity<ActionCardDTO> createActionCard(@RequestBody ActionCardDTO actionCardDTO) {
        ActionCardDTO created = actionCardService.createActionCard(actionCardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ActionCardDTO> updateActionCard(
            @PathVariable @NonNull Long id,
            @RequestBody @NonNull ActionCardDTO actionCardDTO
    ) {
        ActionCardDTO updated = actionCardService.updateActionCard(id, actionCardDTO);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActionCard(@PathVariable @NonNull Long id) {
        actionCardService.deleteActionCard(id);
        return ResponseEntity.noContent().build();
    }
}
