import { useState, useEffect } from 'react'
import { deckAPI } from '../api/api'
import './MetaPage.css'

export default function MetaPage() {
  const [decks, setDecks] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [tierList, setTierList] = useState({
    tier1: [],
    tier2: [],
    tier3: [],
    highPotential: []
  })

  useEffect(() => {
    fetchDecks()
  }, [])

  const fetchDecks = async () => {
    try {
      setLoading(true)
      setError(null)
      const response = await deckAPI.getAll({ sortBy: 'popularity' })
      const allDecks = response.data
      
      // 티어별로 분류
      const organized = {
        tier1: allDecks.filter(d => d.tierRank === 'TIER_1'),
        tier2: allDecks.filter(d => d.tierRank === 'TIER_2'),
        tier3: allDecks.filter(d => d.tierRank === 'TIER_3'),
        highPotential: allDecks.filter(d => d.tierRank === 'HIGH_POTENTIAL')
      }
      
      setTierList(organized)
      setDecks(allDecks)
    } catch (error) {
      console.error('Failed to fetch decks:', error)
      setError('덱 정보를 불러오는데 실패했습니다.')
      // 임시 데이터
      setTierList({
        tier1: [
          { id: 1, deckName: '불꽃 공격 덱', wins: 45, losses: 12, popularityScore: 850 }
        ],
        tier2: [
          { id: 2, deckName: '얼음 제어 덱', wins: 38, losses: 18, popularityScore: 720 },
          { id: 3, deckName: '번개 공명 덱', wins: 35, losses: 20, popularityScore: 680 }
        ],
        tier3: [
          { id: 4, deckName: '바람 서포트 덱', wins: 28, losses: 22, popularityScore: 520 }
        ],
        highPotential: [
          { id: 5, deckName: '실험적 풀 덱', wins: 15, losses: 10, popularityScore: 280 }
        ]
      })
    } finally {
      setLoading(false)
    }
  }

  const getTierBadge = (tier) => {
    const badges = {
      tier1: { label: 'TIER 1', color: '#ff5722' },
      tier2: { label: 'TIER 2', color: '#ff9800' },
      tier3: { label: 'TIER 3', color: '#ffc107' },
      highPotential: { label: 'High Potential', color: '#4caf50' }
    }
    return badges[tier]
  }

  const calculateWinRate = (wins, losses) => {
    const total = wins + losses
    if (total === 0) return 0
    return ((wins / total) * 100).toFixed(1)
  }

  const renderDeckList = (decks, tierKey) => {
    const badge = getTierBadge(tierKey)
    
    return (
      <div className="tier-section">
        <div className="tier-header">
          <div className="tier-badge" style={{ background: badge.color }}>
            {badge.label}
          </div>
          <span className="deck-count">{decks.length} 덱</span>
        </div>
        
        <div className="deck-list">
          {decks.map(deck => (
            <div key={deck.id} className="deck-card">
              <div className="deck-info">
                <h4>{deck.deckName}</h4>
                <div className="deck-stats">
                  <span className="stat">
                    승률: {calculateWinRate(deck.wins, deck.losses)}%
                  </span>
                  <span className="stat">
                    전적: {deck.wins}승 {deck.losses}패
                  </span>
                  <span className="stat">
                    인기도: {deck.popularityScore}
                  </span>
                </div>
              </div>
              <button className="view-deck-btn">덱 보기</button>
            </div>
          ))}
        </div>
      </div>
    )
  }

  return (
    <div className="meta-page">
      <div className="container">
        <div className="page-header">
          <h2>메타 분석 & 티어 리스트</h2>
          <p>최신 토너먼트와 랭크 데이터 기반 덱 순위</p>
        </div>

        {error && <div className="error-message">{error}</div>}

        {loading ? (
          <div className="loading">로딩 중...</div>
        ) : (
          <>
            <div className="stats-overview">
              <div className="stat-card">
                <h3>{decks.length}</h3>
                <p>등록된 덱</p>
              </div>
              <div className="stat-card">
                <h3>{Object.values(tierList).flat().length}</h3>
                <p>고유 덱 타입</p>
              </div>
              <div className="stat-card">
                <h3>최근 2주</h3>
                <p>데이터 기간</p>
              </div>
            </div>

            <div className="tier-lists">
              {tierList.tier1.length > 0 && renderDeckList(tierList.tier1, 'tier1')}
              {tierList.tier2.length > 0 && renderDeckList(tierList.tier2, 'tier2')}
              {tierList.tier3.length > 0 && renderDeckList(tierList.tier3, 'tier3')}
              {tierList.highPotential.length > 0 && renderDeckList(tierList.highPotential, 'highPotential')}
            </div>
          </>
        )}
      </div>
    </div>
  )
}
