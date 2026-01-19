package com.sevensaints.backend.service;

import com.sevensaints.backend.dto.CharacterCardDTO;
import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CharacterCardService {
    
    List<CharacterCardDTO> getAllCharacterCards();
    
    CharacterCardDTO getCharacterCardById(@NonNull Long id);
    
    List<CharacterCardDTO> getCharacterCardsByElement(ElementType elementType);
    
    List<CharacterCardDTO> getCharacterCardsByWeapon(WeaponType weaponType);
    
    List<CharacterCardDTO> getCharacterCardsByFilters(ElementType elementType, WeaponType weaponType);
    
    CharacterCardDTO createCharacterCard(CharacterCardDTO characterCardDTO);
    
    CharacterCardDTO updateCharacterCard(@NonNull Long id, @NonNull CharacterCardDTO characterCardDTO);
    
    void deleteCharacterCard(@NonNull Long id);
}
