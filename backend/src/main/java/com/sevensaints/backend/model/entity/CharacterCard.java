package com.sevensaints.backend.model.entity;

import com.sevensaints.backend.model.enums.ElementType;
import com.sevensaints.backend.model.enums.WeaponType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "card_id", unique = true)
    private Card card;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "element_type", nullable = false, length = 20)
    private ElementType elementType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "weapon_type", nullable = false, length = 20)
    private WeaponType weaponType;
    
    @Column(name = "hp", nullable = false)
    private Integer hp;
    
    @Column(name = "max_energy", nullable = false)
    private Integer maxEnergy;
    
    @Column(name = "faction", length = 50)
    private String faction;
    
    @OneToMany(mappedBy = "characterCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterSkill> skills = new ArrayList<>();
}
