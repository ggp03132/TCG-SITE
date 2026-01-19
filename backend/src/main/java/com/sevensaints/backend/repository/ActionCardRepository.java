package com.sevensaints.backend.repository;

import com.sevensaints.backend.model.entity.ActionCard;
import com.sevensaints.backend.model.enums.ActionType;
import com.sevensaints.backend.model.enums.ElementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionCardRepository extends JpaRepository<ActionCard, Long> {
    
    List<ActionCard> findByActionType(ActionType actionType);
    
    List<ActionCard> findByElementType(ElementType elementType);
    
    Optional<ActionCard> findByCardId(Long cardId);
    
    @Query("SELECT ac FROM ActionCard ac JOIN FETCH ac.card ORDER BY ac.actionType, ac.card.cardName")
    List<ActionCard> findAllWithDetails();
}
