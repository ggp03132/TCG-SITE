# 일곱성인의 소환 (Seven Saints Summon) 데이터베이스

일곱성인의 소환 카드 게임을 위한 종합 웹 데이터베이스입니다.

## 🚀 실행 중인 애플리케이션

> **로컬 개발 환경에서 실행 시 접속 주소:**
> - **Frontend**: http://localhost:5173
> - **Backend API**: http://localhost:8080/api
> - **H2 Database Console**: http://localhost:8080/h2-console

현재는 로컬 개발 서버로 실행됩니다. 프로젝트를 클론한 후 아래 실행 방법을 따라주세요.

## 프로젝트 구조

```
seven-saints-summon/
├── backend/          # Spring Boot 백엔드
├── frontend/         # React 프론트엔드
└── README.md
```

## 주요 기능

- 📇 **카드 데이터베이스**: 전체 캐릭터 카드 및 행동 카드 정보
- 🎴 **덱 빌더**: 덱 구성 및 공유 도구
- 📊 **메타 분석**: 티어 리스트 및 인기 덱 통계
- 🔍 **고급 검색**: 원소, 무기, 비용별 필터링

## 기술 스택

### Backend
- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- H2 Database (development)
- PostgreSQL (production)
- Maven

### Frontend
- React 18
- Vite
- React Router
- Axios

## 시작하기

### 사전 요구사항
- Java 17+
- Node.js 18+
- Maven

### 빠른 시작 (H2 In-Memory Database 사용)

현재 프로젝트는 H2 인메모리 데이터베이스를 사용하여 별도의 데이터베이스 설치 없이 실행 가능합니다.

### Backend 실행

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend는 **http://localhost:8080/api** 에서 실행됩니다.
H2 콘솔은 **http://localhost:8080/h2-console** 에서 접속 가능합니다.
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (비워두기)

### Frontend 실행

```bash
cd frontend
npm install
npm run dev
```

Frontend는 **http://localhost:5173** 에서 실행됩니다.

### PostgreSQL 사용 (선택사항)
### PostgreSQL 사용 (선택사항)

프로덕션 환경에서는 PostgreSQL을 사용할 수 있습니다.

1. PostgreSQL 데이터베이스 생성:
```sql
CREATE DATABASE seven_saints_db;
```

2. 사용자 설정 (필요시):
```sql
CREATE USER postgres WITH PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE seven_saints_db TO postgres;
```

3. `application.yml`에서 H2 설정을 PostgreSQL로 변경

## 실행 확인

프로젝트가 정상적으로 실행되면:
1. Frontend: http://localhost:5173 에서 캐릭터 카드 목록 확인
2. Backend API 테스트: http://localhost:8080/api/cards/characters
3. H2 콘솔: http://localhost:8080/h2-console 에서 데이터베이스 확인

## 개발 로드맵

- [x] 프로젝트 초기 설정
- [x] 데이터베이스 스키마 설계
- [x] Repository 레이어 구현
- [x] Service 레이어 구현
- [x] REST API Controller 구현
- [x] DTO 클래스 생성
- [x] 프론트엔드 API 연동
- [x] H2 인메모리 데이터베이스 설정
- [x] 샘플 데이터 입력
- [ ] 덱 빌더 UI 구현
- [ ] 이미지 업로드 및 관리
- [ ] 사용자 인증 시스템

## API 엔드포인트

### 캐릭터 카드
- `GET /api/cards/characters` - 모든 캐릭터 카드 조회 (필터: element, weapon)
- `GET /api/cards/characters/{id}` - 특정 캐릭터 카드 조회
- `POST /api/cards/characters` - 캐릭터 카드 생성
- `PUT /api/cards/characters/{id}` - 캐릭터 카드 수정
- `DELETE /api/cards/characters/{id}` - 캐릭터 카드 삭제

### 행동 카드
- `GET /api/cards/actions` - 모든 행동 카드 조회 (필터: type)
- `GET /api/cards/actions/{id}` - 특정 행동 카드 조회
- `POST /api/cards/actions` - 행동 카드 생성
- `PUT /api/cards/actions/{id}` - 행동 카드 수정
- `DELETE /api/cards/actions/{id}` - 행동 카드 삭제

### 덱
- `GET /api/decks` - 모든 덱 조회 (필터: tier, sortBy=popularity|winrate)
- `GET /api/decks/{id}` - 특정 덱 조회
- `POST /api/decks` - 덱 생성
- `PUT /api/decks/{id}` - 덱 수정
- `DELETE /api/decks/{id}` - 덱 삭제

## 라이선스

MIT License
