package com.sevensaints.backend.dto;

import com.sevensaints.backend.model.enums.SkillType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSkillDTO {
    private Long id;
    private String skillName;
    private SkillType skillType;
    private Integer damage;
    private String costType;
    private Integer costAmount;
    private String description;
    private Integer skillOrder;
}
