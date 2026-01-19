-- 샘플 캐릭터 카드 데이터

-- 1. 다이루크 (Diluc) - 불 원소, 양손검
INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('다이루크', 'CHARACTER', '몬드의 밤을 지키는 다크 히어로', '/images/characters/diluc.png');

INSERT INTO character_cards (card_id, element_type, weapon_type, hp, max_energy, faction) VALUES 
(1, 'PYRO', 'CLAYMORE', 10, 3, 'MONDSTADT');

INSERT INTO character_skills (character_card_id, skill_name, skill_type, damage, cost_type, cost_amount, description, skill_order) VALUES 
(1, '단련된 검술', 'NORMAL_ATTACK', 2, 'SAME', 1, '일반 공격으로 2의 물리 피해를 가합니다.', 1),
(1, '역린', 'ELEMENTAL_SKILL', 3, 'SAME', 3, '3의 불 원소 피해를 가합니다.', 2),
(1, '여명', 'ELEMENTAL_BURST', 8, 'SAME', 4, '8의 불 원소 피해를 가합니다. 불의 새 소환.', 3);

-- 2. 감우 (Ganyu) - 얼음 원소, 활
INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('감우', 'CHARACTER', '월해정의 비서로 활약하는 선인의 혈통', '/images/characters/ganyu.png');

INSERT INTO character_cards (card_id, element_type, weapon_type, hp, max_energy, faction) VALUES 
(2, 'CRYO', 'BOW', 10, 2, 'LIYUE');

INSERT INTO character_skills (character_card_id, skill_name, skill_type, damage, cost_type, cost_amount, description, skill_order) VALUES 
(2, '유천 사격', 'NORMAL_ATTACK', 2, 'SAME', 1, '일반 공격으로 2의 물리 피해를 가합니다.', 1),
(2, '산택빙화', 'ELEMENTAL_SKILL', 2, 'SAME', 3, '2의 얼음 원소 피해를 가합니다. 빙연화 소환.', 2),
(2, '강천만상', 'ELEMENTAL_BURST', 1, 'SAME', 3, '1의 얼음 원소 피해를 가합니다. 빙영주 소환.', 3);

-- 3. 라이덴 쇼군 (Raiden Shogun) - 번개 원소, 장병기
INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('라이덴 쇼군', 'CHARACTER', '이나즈마의 번개 신', '/images/characters/raiden.png');

INSERT INTO character_cards (card_id, element_type, weapon_type, hp, max_energy, faction) VALUES 
(3, 'ELECTRO', 'POLEARM', 10, 2, 'INAZUMA');

INSERT INTO character_skills (character_card_id, skill_name, skill_type, damage, cost_type, cost_amount, description, skill_order) VALUES 
(3, '원류', 'NORMAL_ATTACK', 2, 'SAME', 1, '일반 공격으로 2의 물리 피해를 가합니다.', 1),
(3, '신변·악요개안', 'ELEMENTAL_SKILL', 2, 'SAME', 3, '2의 번개 원소 피해를 가합니다. 뇌문 부여.', 2),
(3, '오의·몽상진수', 'ELEMENTAL_BURST', 3, 'SAME', 4, '3의 번개 원소 피해를 가합니다. 제찰의 빛 충전.', 3);

-- 샘플 행동 카드 데이터

-- 장비 카드
INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('천공의 칼날', 'ACTION', '고대 문명이 남긴 대검', '/images/actions/skyward_pride.png');

INSERT INTO action_cards (card_id, action_type, cost, weapon_type, effect_description) VALUES 
(4, 'EQUIPMENT', 3, 'CLAYMORE', '장착한 캐릭터가 일반 공격을 가할 때 피해 +1');

INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('아모스의 활', 'ACTION', '고대의 명궁', '/images/actions/amos_bow.png');

INSERT INTO action_cards (card_id, action_type, cost, weapon_type, effect_description) VALUES 
(5, 'EQUIPMENT', 3, 'BOW', '장착한 캐릭터가 일반 공격을 가할 때 피해 +1');

-- 지원 카드
INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('캐서린', 'ACTION', '모험가 길드의 접수원', '/images/actions/katheryne.png');

INSERT INTO action_cards (card_id, action_type, cost, effect_description) VALUES 
(6, 'SUPPORT', 1, '행동 종료 시: 주사위 1개를 다시 굴림');

INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('티미', 'ACTION', '몬드성의 소년', '/images/actions/timmie.png');

INSERT INTO action_cards (card_id, action_type, cost, effect_description) VALUES 
(7, 'SUPPORT', 2, '매 라운드 주사위 1개 생성');

-- 이벤트 카드
INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('원소 공명: 치솟는 불길', 'ACTION', '불 원소 공명 효과', '/images/actions/pyro_resonance.png');

INSERT INTO action_cards (card_id, action_type, cost, element_type, effect_description) VALUES 
(8, 'EVENT', 1, 'PYRO', '다음에 우리 캐릭터가 생성하는 불 원소 주사위 개수 +2');

INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('원소 공명: 부서지지 않는 얼음', 'ACTION', '얼음 원소 공명 효과', '/images/actions/cryo_resonance.png');

INSERT INTO action_cards (card_id, action_type, cost, element_type, effect_description) VALUES 
(9, 'EVENT', 1, 'CRYO', '다음에 우리 캐릭터가 생성하는 얼음 원소 주사위 개수 +2');

-- 요리 카드
INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('달콤달콤 닭고기 스튜', 'ACTION', '회복 요리', '/images/actions/sweet_madame.png');

INSERT INTO action_cards (card_id, action_type, cost, effect_description) VALUES 
(10, 'FOOD', 0, '활성 캐릭터를 2 치료합니다.');

INSERT INTO cards (card_name, card_type, description, image_url) VALUES 
('몬드 감자병', 'ACTION', '공격력 증가 요리', '/images/actions/mondstadt_hash_brown.png');

INSERT INTO action_cards (card_id, action_type, cost, effect_description) VALUES 
(11, 'FOOD', 1, '이번 라운드 활성 캐릭터의 다음 일반 공격 피해 +1');

-- 샘플 덱 데이터
INSERT INTO decks (deck_name, description, author, tier_rank, wins, losses, popularity_score) VALUES 
('불꽃 공격 덱', '다이루크를 중심으로 한 공격적인 불 원소 덱', '유저123', 'TIER_1', 45, 12, 850);

-- 덱에 카드 추가
INSERT INTO deck_cards (deck_id, card_id, quantity) VALUES 
(1, 1, 1), -- 다이루크
(1, 4, 1), -- 천공의 칼날
(1, 8, 2), -- 불 원소 공명
(1, 10, 2); -- 달콤달콤 닭고기 스튜

INSERT INTO decks (deck_name, description, author, tier_rank, wins, losses, popularity_score) VALUES 
('얼음 제어 덱', '감우 기반의 제어형 덱', '유저456', 'TIER_2', 38, 18, 720);

INSERT INTO deck_cards (deck_id, card_id, quantity) VALUES 
(2, 2, 1), -- 감우
(2, 5, 1), -- 아모스의 활
(2, 9, 2), -- 얼음 원소 공명
(2, 6, 1); -- 캐서린
