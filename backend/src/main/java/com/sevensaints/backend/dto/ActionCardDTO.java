package com.sevensaints.backend.dto;

import com.sevensaints.backend.model.enums.ActionType;
import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionCardDTO {
    private Long id;
    private String cardName;
    private String description;
    private String imageUrl;
    private ActionType actionType;
    private Integer cost;
    private ElementType elementType;
    private WeaponType weaponType;
    private String effectDescription;
}
