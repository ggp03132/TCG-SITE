package com.sevensaints.backend.service;

import com.sevensaints.backend.dto.ActionCardDTO;
import com.sevensaints.backend.model.enums.ActionType;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ActionCardService {
    
    List<ActionCardDTO> getAllActionCards();
    
    ActionCardDTO getActionCardById(@NonNull Long id);
    
    List<ActionCardDTO> getActionCardsByType(ActionType actionType);
    
    ActionCardDTO createActionCard(ActionCardDTO actionCardDTO);
    
    ActionCardDTO updateActionCard(@NonNull Long id, @NonNull ActionCardDTO actionCardDTO);
    
    void deleteActionCard(@NonNull Long id);
}
