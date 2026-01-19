package com.sevensaints.backend.service.impl;

import com.sevensaints.backend.dto.CharacterCardDTO;
import com.sevensaints.backend.dto.CharacterSkillDTO;
import com.sevensaints.backend.model.entity.Card;
import com.sevensaints.backend.model.entity.CharacterCard;
import com.sevensaints.backend.model.entity.CharacterSkill;
import com.sevensaints.backend.model.enums.CardType;
import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import com.sevensaints.backend.repository.CardRepository;
import com.sevensaints.backend.repository.CharacterCardRepository;
import com.sevensaints.backend.service.CharacterCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterCardServiceImpl implements CharacterCardService {
    
    private final CharacterCardRepository characterCardRepository;
    private final CardRepository cardRepository;
    
    @Override
    public List<CharacterCardDTO> getAllCharacterCards() {
        return characterCardRepository.findAllWithDetails().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public CharacterCardDTO getCharacterCardById(@NonNull Long id) {
        CharacterCard characterCard = characterCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character card not found with id: " + id));
        return convertToDTO(characterCard);
    }
    
    @Override
    public List<CharacterCardDTO> getCharacterCardsByElement(ElementType elementType) {
        return characterCardRepository.findByElementType(elementType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CharacterCardDTO> getCharacterCardsByWeapon(WeaponType weaponType) {
        return characterCardRepository.findByWeaponType(weaponType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CharacterCardDTO> getCharacterCardsByFilters(ElementType elementType, WeaponType weaponType) {
        if (elementType != null && weaponType != null) {
            return characterCardRepository.findByElementTypeAndWeaponType(elementType, weaponType).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else if (elementType != null) {
            return getCharacterCardsByElement(elementType);
        } else if (weaponType != null) {
            return getCharacterCardsByWeapon(weaponType);
        } else {
            return getAllCharacterCards();
        }
    }
    
    @Override
    @Transactional
    public CharacterCardDTO createCharacterCard(CharacterCardDTO dto) {
        // Create Card entity
        Card card = new Card();
        card.setCardName(dto.getCardName());
        card.setCardType(CardType.CHARACTER);
        card.setDescription(dto.getDescription());
        card.setImageUrl(dto.getImageUrl());
        card = cardRepository.save(card);
        
        // Create CharacterCard entity
        CharacterCard characterCard = new CharacterCard();
        characterCard.setCard(card);
        characterCard.setElementType(dto.getElementType());
        characterCard.setWeaponType(dto.getWeaponType());
        characterCard.setHp(dto.getHp());
        characterCard.setMaxEnergy(dto.getMaxEnergy());
        characterCard.setFaction(dto.getFaction());
        
        // Add skills
        if (dto.getSkills() != null && !dto.getSkills().isEmpty()) {
            for (CharacterSkillDTO skillDTO : dto.getSkills()) {
                CharacterSkill skill = new CharacterSkill();
                skill.setCharacterCard(characterCard);
                skill.setSkillName(skillDTO.getSkillName());
                skill.setSkillType(skillDTO.getSkillType());
                skill.setDamage(skillDTO.getDamage());
                skill.setCostType(skillDTO.getCostType());
                skill.setCostAmount(skillDTO.getCostAmount());
                skill.setDescription(skillDTO.getDescription());
                skill.setSkillOrder(skillDTO.getSkillOrder());
                characterCard.getSkills().add(skill);
            }
        }
        
        characterCard = characterCardRepository.save(characterCard);
        return convertToDTO(characterCard);
    }
    
    @Override
    @Transactional
    public CharacterCardDTO updateCharacterCard(@NonNull Long id, @NonNull CharacterCardDTO dto) {
        CharacterCard characterCard = characterCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character card not found with id: " + id));
        
        // Update Card
        Card card = characterCard.getCard();
        card.setCardName(dto.getCardName());
        card.setDescription(dto.getDescription());
        card.setImageUrl(dto.getImageUrl());
        
        // Update CharacterCard
        characterCard.setElementType(dto.getElementType());
        characterCard.setWeaponType(dto.getWeaponType());
        characterCard.setHp(dto.getHp());
        characterCard.setMaxEnergy(dto.getMaxEnergy());
        characterCard.setFaction(dto.getFaction());
        
        // Update skills
        characterCard.getSkills().clear();
        if (dto.getSkills() != null && !dto.getSkills().isEmpty()) {
            for (CharacterSkillDTO skillDTO : dto.getSkills()) {
                CharacterSkill skill = new CharacterSkill();
                skill.setCharacterCard(characterCard);
                skill.setSkillName(skillDTO.getSkillName());
                skill.setSkillType(skillDTO.getSkillType());
                skill.setDamage(skillDTO.getDamage());
                skill.setCostType(skillDTO.getCostType());
                skill.setCostAmount(skillDTO.getCostAmount());
                skill.setDescription(skillDTO.getDescription());
                skill.setSkillOrder(skillDTO.getSkillOrder());
                characterCard.getSkills().add(skill);
            }
        }
        
        characterCard = characterCardRepository.save(characterCard);
        return convertToDTO(characterCard);
    }
    
    @Override
    @Transactional
    @SuppressWarnings("null") // characterCard is guaranteed to be non-null by orElseThrow
    public void deleteCharacterCard(@NonNull Long id) {
        CharacterCard characterCard = characterCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character card not found with id: " + id));
        characterCardRepository.delete(characterCard);
    }
    
    private CharacterCardDTO convertToDTO(CharacterCard entity) {
        CharacterCardDTO dto = new CharacterCardDTO();
        dto.setId(entity.getId());
        dto.setCardName(entity.getCard().getCardName());
        dto.setDescription(entity.getCard().getDescription());
        dto.setImageUrl(entity.getCard().getImageUrl());
        dto.setElementType(entity.getElementType());
        dto.setWeaponType(entity.getWeaponType());
        dto.setHp(entity.getHp());
        dto.setMaxEnergy(entity.getMaxEnergy());
        dto.setFaction(entity.getFaction());
        
        if (entity.getSkills() != null && !entity.getSkills().isEmpty()) {
            List<CharacterSkillDTO> skillDTOs = entity.getSkills().stream()
                    .map(this::convertSkillToDTO)
                    .collect(Collectors.toList());
            dto.setSkills(skillDTOs);
        }
        
        return dto;
    }
    
    private CharacterSkillDTO convertSkillToDTO(CharacterSkill entity) {
        CharacterSkillDTO dto = new CharacterSkillDTO();
        dto.setId(entity.getId());
        dto.setSkillName(entity.getSkillName());
        dto.setSkillType(entity.getSkillType());
        dto.setDamage(entity.getDamage());
        dto.setCostType(entity.getCostType());
        dto.setCostAmount(entity.getCostAmount());
        dto.setDescription(entity.getDescription());
        dto.setSkillOrder(entity.getSkillOrder());
        return dto;
    }
}
