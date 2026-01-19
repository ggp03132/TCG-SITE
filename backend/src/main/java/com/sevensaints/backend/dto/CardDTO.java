package com.sevensaints.backend.dto;

import com.sevensaints.backend.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    private String cardName;
    private CardType cardType;
    private String description;
    private String imageUrl;
}
