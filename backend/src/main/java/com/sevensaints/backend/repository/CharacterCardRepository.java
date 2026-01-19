package com.sevensaints.backend.repository;

import com.sevensaints.backend.model.entity.CharacterCard;
import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterCardRepository extends JpaRepository<CharacterCard, Long> {
    
    List<CharacterCard> findByElementType(ElementType elementType);
    
    List<CharacterCard> findByWeaponType(WeaponType weaponType);
    
    List<CharacterCard> findByElementTypeAndWeaponType(ElementType elementType, WeaponType weaponType);
    
    Optional<CharacterCard> findByCardId(Long cardId);
    
    @Query("SELECT cc FROM CharacterCard cc JOIN FETCH cc.card JOIN FETCH cc.skills ORDER BY cc.card.cardName")
    List<CharacterCard> findAllWithDetails();
}
