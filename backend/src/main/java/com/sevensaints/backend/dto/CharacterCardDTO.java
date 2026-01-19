package com.sevensaints.backend.dto;

import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCardDTO {
    private Long id;
    private String cardName;
    private String description;
    private String imageUrl;
    private ElementType elementType;
    private WeaponType weaponType;
    private Integer hp;
    private Integer maxEnergy;
    private String faction;
    private List<CharacterSkillDTO> skills = new ArrayList<>();
}
