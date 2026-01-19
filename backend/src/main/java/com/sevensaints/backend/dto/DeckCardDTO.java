package com.sevensaints.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeckCardDTO {
    private Long cardId;
    private String cardName;
    private String cardType;
    private Integer quantity;
    private String imageUrl;
}
