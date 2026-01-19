package com.sevensaints.backend.dto;

import com.sevensaints.backend.model.enums.TierRank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeckDTO {
    private Long id;
    private String deckName;
    private String description;
    private String author;
    private TierRank tierRank;
    private Integer wins;
    private Integer losses;
    private Integer popularityScore;
    private List<DeckCardDTO> cards = new ArrayList<>();
    
    public Double getWinRate() {
        int total = wins + losses;
        if (total == 0) return 0.0;
        return (wins * 100.0) / total;
    }
}
