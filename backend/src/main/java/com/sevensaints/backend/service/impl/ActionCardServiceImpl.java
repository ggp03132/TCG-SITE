package com.sevensaints.backend.service.impl;

import com.sevensaints.backend.dto.ActionCardDTO;
import com.sevensaints.backend.model.entity.ActionCard;
import com.sevensaints.backend.model.entity.Card;
import com.sevensaints.backend.model.enums.ActionType;
import com.sevensaints.backend.model.enums.CardType;
import com.sevensaints.backend.repository.ActionCardRepository;
import com.sevensaints.backend.repository.CardRepository;
import com.sevensaints.backend.service.ActionCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActionCardServiceImpl implements ActionCardService {
    
    private final ActionCardRepository actionCardRepository;
    private final CardRepository cardRepository;
    
    @Override
    public List<ActionCardDTO> getAllActionCards() {
        return actionCardRepository.findAllWithDetails().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public ActionCardDTO getActionCardById(@NonNull Long id) {
        ActionCard actionCard = actionCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Action card not found with id: " + id));
        return convertToDTO(actionCard);
    }
    
    @Override
    public List<ActionCardDTO> getActionCardsByType(ActionType actionType) {
        return actionCardRepository.findByActionType(actionType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public ActionCardDTO createActionCard(ActionCardDTO dto) {
        // Create Card entity
        Card card = new Card();
        card.setCardName(dto.getCardName());
        card.setCardType(CardType.ACTION);
        card.setDescription(dto.getDescription());
        card.setImageUrl(dto.getImageUrl());
        card = cardRepository.save(card);
        
        // Create ActionCard entity
        ActionCard actionCard = new ActionCard();
        actionCard.setCard(card);
        actionCard.setActionType(dto.getActionType());
        actionCard.setCost(dto.getCost());
        actionCard.setElementType(dto.getElementType());
        actionCard.setWeaponType(dto.getWeaponType());
        actionCard.setEffectDescription(dto.getEffectDescription());
        
        actionCard = actionCardRepository.save(actionCard);
        return convertToDTO(actionCard);
    }
    
    @Override
    @Transactional
    public ActionCardDTO updateActionCard(@NonNull Long id, @NonNull ActionCardDTO dto) {
        ActionCard actionCard = actionCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Action card not found with id: " + id));
        
        // Update Card
        Card card = actionCard.getCard();
        card.setCardName(dto.getCardName());
        card.setDescription(dto.getDescription());
        card.setImageUrl(dto.getImageUrl());
        
        // Update ActionCard
        actionCard.setActionType(dto.getActionType());
        actionCard.setCost(dto.getCost());
        actionCard.setElementType(dto.getElementType());
        actionCard.setWeaponType(dto.getWeaponType());
        actionCard.setEffectDescription(dto.getEffectDescription());
        
        actionCard = actionCardRepository.save(actionCard);
        return convertToDTO(actionCard);
    }
    
    @Override
    @Transactional
    @SuppressWarnings("null") // actionCard is guaranteed to be non-null by orElseThrow
    public void deleteActionCard(@NonNull Long id) {
        ActionCard actionCard = actionCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Action card not found with id: " + id));
        actionCardRepository.delete(actionCard);
    }
    
    private ActionCardDTO convertToDTO(ActionCard entity) {
        ActionCardDTO dto = new ActionCardDTO();
        dto.setId(entity.getId());
        dto.setCardName(entity.getCard().getCardName());
        dto.setDescription(entity.getCard().getDescription());
        dto.setImageUrl(entity.getCard().getImageUrl());
        dto.setActionType(entity.getActionType());
        dto.setCost(entity.getCost());
        dto.setElementType(entity.getElementType());
        dto.setWeaponType(entity.getWeaponType());
        dto.setEffectDescription(entity.getEffectDescription());
        return dto;
    }
}
