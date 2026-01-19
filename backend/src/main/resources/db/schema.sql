-- 데이터베이스 초기화
DROP TABLE IF EXISTS deck_cards;
DROP TABLE IF EXISTS decks;
DROP TABLE IF EXISTS character_skills;
DROP TABLE IF EXISTS character_cards;
DROP TABLE IF EXISTS action_cards;
DROP TABLE IF EXISTS cards;

-- 카드 기본 테이블
CREATE TABLE cards (
    id BIGSERIAL PRIMARY KEY,
    card_name VARCHAR(100) NOT NULL,
    card_type VARCHAR(20) NOT NULL, -- CHARACTER, ACTION
    description TEXT,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 캐릭터 카드 테이블
CREATE TABLE character_cards (
    id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES cards(id) ON DELETE CASCADE,
    element_type VARCHAR(20) NOT NULL, -- PYRO, HYDRO, ELECTRO, CRYO, DENDRO, ANEMO, GEO
    weapon_type VARCHAR(20) NOT NULL, -- SWORD, CLAYMORE, POLEARM, BOW, CATALYST
    hp INTEGER NOT NULL,
    max_energy INTEGER NOT NULL,
    faction VARCHAR(50),
    UNIQUE(card_id)
);

-- 캐릭터 스킬 테이블
CREATE TABLE character_skills (
    id BIGSERIAL PRIMARY KEY,
    character_card_id BIGINT REFERENCES character_cards(id) ON DELETE CASCADE,
    skill_name VARCHAR(100) NOT NULL,
    skill_type VARCHAR(20) NOT NULL, -- NORMAL_ATTACK, ELEMENTAL_SKILL, ELEMENTAL_BURST, PASSIVE
    damage INTEGER DEFAULT 0,
    cost_type VARCHAR(20), -- SAME, DIFFERENT, ENERGY
    cost_amount INTEGER DEFAULT 0,
    description TEXT,
    skill_order INTEGER DEFAULT 0
);

-- 행동 카드 테이블
CREATE TABLE action_cards (
    id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES cards(id) ON DELETE CASCADE,
    action_type VARCHAR(30) NOT NULL, -- EQUIPMENT, SUPPORT, EVENT, FOOD
    cost INTEGER NOT NULL,
    element_type VARCHAR(20), -- 원소 관련 카드인 경우
    weapon_type VARCHAR(20), -- 무기 관련 카드인 경우
    effect_description TEXT,
    UNIQUE(card_id)
);

-- 덱 테이블
CREATE TABLE decks (
    id BIGSERIAL PRIMARY KEY,
    deck_name VARCHAR(100) NOT NULL,
    description TEXT,
    author VARCHAR(100),
    tier_rank VARCHAR(10), -- TIER_1, TIER_2, TIER_3, ROGUE
    wins INTEGER DEFAULT 0,
    losses INTEGER DEFAULT 0,
    popularity_score INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 덱-카드 연관 테이블
CREATE TABLE deck_cards (
    id BIGSERIAL PRIMARY KEY,
    deck_id BIGINT REFERENCES decks(id) ON DELETE CASCADE,
    card_id BIGINT REFERENCES cards(id) ON DELETE CASCADE,
    quantity INTEGER DEFAULT 1,
    UNIQUE(deck_id, card_id)
);

-- 인덱스 생성
CREATE INDEX idx_cards_type ON cards(card_type);
CREATE INDEX idx_character_element ON character_cards(element_type);
CREATE INDEX idx_character_weapon ON character_cards(weapon_type);
CREATE INDEX idx_action_type ON action_cards(action_type);
CREATE INDEX idx_decks_tier ON decks(tier_rank);
CREATE INDEX idx_deck_cards_deck ON deck_cards(deck_id);
