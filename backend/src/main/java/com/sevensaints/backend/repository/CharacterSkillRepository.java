package com.sevensaints.backend.repository;

import com.sevensaints.backend.model.entity.CharacterSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterSkillRepository extends JpaRepository<CharacterSkill, Long> {
    
    List<CharacterSkill> findByCharacterCardIdOrderBySkillOrder(Long characterCardId);
    
    void deleteByCharacterCardId(Long characterCardId);
}
