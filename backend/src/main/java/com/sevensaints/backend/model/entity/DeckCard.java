package com.sevensaints.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deck_cards", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"deck_id", "card_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeckCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;
    
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
    
    @Column(name = "quantity")
    private Integer quantity = 1;
}
