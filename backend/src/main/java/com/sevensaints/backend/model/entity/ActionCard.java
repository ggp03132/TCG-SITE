package com.sevensaints.backend.model.entity;

import com.sevensaints.backend.model.enums.ActionType;
import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "action_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "card_id", unique = true)
    private Card card;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false, length = 30)
    private ActionType actionType;
    
    @Column(name = "cost", nullable = false)
    private Integer cost;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "element_type", length = 20)
    private ElementType elementType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "weapon_type", length = 20)
    private WeaponType weaponType;
    
    @Column(name = "effect_description", columnDefinition = "TEXT")
    private String effectDescription;
}
