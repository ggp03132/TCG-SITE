package com.sevensaints.backend.model.entity;

import com.sevensaints.backend.model.enums.SkillType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "character_skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSkill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "character_card_id")
    private CharacterCard characterCard;
    
    @Column(name = "skill_name", nullable = false, length = 100)
    private String skillName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_type", nullable = false, length = 20)
    private SkillType skillType;
    
    @Column(name = "damage")
    private Integer damage = 0;
    
    @Column(name = "cost_type", length = 20)
    private String costType; // SAME, DIFFERENT, ENERGY
    
    @Column(name = "cost_amount")
    private Integer costAmount = 0;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "skill_order")
    private Integer skillOrder = 0;
}
