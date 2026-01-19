package com.sevensaints.backend.model.entity;

import com.sevensaints.backend.model.enums.TierRank;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "decks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deck {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "deck_name", nullable = false, length = 100)
    private String deckName;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "author", length = 100)
    private String author;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tier_rank", length = 10)
    private TierRank tierRank;
    
    @Column(name = "wins")
    private Integer wins = 0;
    
    @Column(name = "losses")
    private Integer losses = 0;
    
    @Column(name = "popularity_score")
    private Integer popularityScore = 0;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeckCard> deckCards = new ArrayList<>();
}
