import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import CharactersPage from './components/CharactersPage'
import MetaPage from './components/MetaPage'
import './App.css'

function App() {
  return (
    <Router>
      <div className="app">
        <header className="header">
          <div className="container">
            <h1 className="logo">일곱성인의 소환</h1>
            <nav className="nav">
              <Link to="/">홈</Link>
              <Link to="/characters">캐릭터 카드</Link>
              <Link to="/actions">행동 카드</Link>
              <Link to="/decks">덱 빌더</Link>
              <Link to="/meta">메타 분석</Link>
            </nav>
          </div>
        </header>

        <main className="main">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/characters" element={<CharactersPage />} />
            <Route path="/actions" element={<ActionsPage />} />
            <Route path="/decks" element={<DecksPage />} />
            <Route path="/meta" element={<MetaPage />} />
          </Routes>
        </main>

        <footer className="footer">
          <div className="container">
            <p>&copy; 2026 일곱성인의 소환 데이터베이스</p>
          </div>
        </footer>
      </div>
    </Router>
  )
}

function HomePage() {
  return (
    <div className="container">
      <section className="hero">
        <h2>일곱성인의 소환 데이터베이스에 오신 것을 환영합니다</h2>
        <p>모든 카드 정보, 덱 구성, 메타 분석을 한 곳에서</p>
      </section>
      
      <section className="features">
        <div className="feature-card">
          <h3>📇 카드 데이터베이스</h3>
          <p>전체 캐릭터 카드와 행동 카드 정보</p>
        </div>
        <div className="feature-card">
          <h3>🎴 덱 빌더</h3>
          <p>나만의 덱을 구성하고 공유하세요</p>
        </div>
        <div className="feature-card">
          <h3>📊 메타 분석</h3>
          <p>인기 덱과 티어 리스트 확인</p>
        </div>
      </section>
    </div>
  )
}



function ActionsPage() {
  return (
    <div className="container">
      <h2>행동 카드</h2>
      <p>행동 카드 데이터베이스 (개발 중)</p>
    </div>
  )
}

function DecksPage() {
  return (
    <div className="container">
      <h2>덱 빌더</h2>
      <p>덱 구성 도구 (개발 중)</p>
    </div>
  )
}

export default App
