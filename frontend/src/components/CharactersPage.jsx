import { useState, useEffect } from 'react'
import { characterCardAPI } from '../api/api'
import './CharactersPage.css'

export default function CharactersPage() {
  const [characters, setCharacters] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [filters, setFilters] = useState({
    element: '',
    weapon: '',
    faction: ''
  })

  useEffect(() => {
    fetchCharacters()
  }, [filters.element, filters.weapon])

  const fetchCharacters = async () => {
    try {
      setLoading(true)
      setError(null)
      const params = {}
      if (filters.element) params.element = filters.element
      if (filters.weapon) params.weapon = filters.weapon
      
      const response = await characterCardAPI.getAll(params)
      setCharacters(response.data)
    } catch (error) {
      console.error('Failed to fetch characters:', error)
      setError('캐릭터 카드를 불러오는데 실패했습니다.')
      // 임시 데이터 사용
      setCharacters([
        {
          id: 1,
          cardName: '다이루크',
          elementType: 'PYRO',
          weaponType: 'CLAYMORE',
          hp: 10,
          maxEnergy: 3,
          imageUrl: '/images/characters/diluc.png'
        },
        {
          id: 2,
          cardName: '감우',
          elementType: 'CRYO',
          weaponType: 'BOW',
          hp: 10,
          maxEnergy: 2,
          imageUrl: '/images/characters/ganyu.png'
        },
        {
          id: 3,
          cardName: '라이덴 쇼군',
          elementType: 'ELECTRO',
          weaponType: 'POLEARM',
          hp: 10,
          maxEnergy: 2,
          imageUrl: '/images/characters/raiden.png'
        }
      ])
    } finally {
      setLoading(false)
    }
  }

  const elementIcons = {
    PYRO: '🔥',
    HYDRO: '💧',
    ELECTRO: '⚡',
    CRYO: '❄️',
    DENDRO: '🌿',
    ANEMO: '💨',
    GEO: '🪨'
  }

  const weaponIcons = {
    SWORD: '⚔️',
    CLAYMORE: '🗡️',
    POLEARM: '🔱',
    BOW: '🏹',
    CATALYST: '📖'
  }

  return (
    <div className="characters-page">
      <div className="container">
        <h2>캐릭터 카드 데이터베이스</h2>
        
        {error && <div className="error-message">{error}</div>}
        
        <div className="filters">
          <div className="filter-group">
            <label>원소:</label>
            <select 
              value={filters.element} 
              onChange={(e) => setFilters({...filters, element: e.target.value})}
            >
              <option value="">전체</option>
              <option value="PYRO">🔥 불</option>
              <option value="HYDRO">💧 물</option>
              <option value="ELECTRO">⚡ 번개</option>
              <option value="CRYO">❄️ 얼음</option>
              <option value="DENDRO">🌿 풀</option>
              <option value="ANEMO">💨 바람</option>
              <option value="GEO">🪨 바위</option>
            </select>
          </div>

          <div className="filter-group">
            <label>무기:</label>
            <select 
              value={filters.weapon} 
              onChange={(e) => setFilters({...filters, weapon: e.target.value})}
            >
              <option value="">전체</option>
              <option value="SWORD">⚔️ 한손검</option>
              <option value="CLAYMORE">🗡️ 양손검</option>
              <option value="POLEARM">🔱 장병기</option>
              <option value="BOW">🏹 활</option>
              <option value="CATALYST">📖 법구</option>
            </select>
          </div>
        </div>

        {loading ? (
          <div className="loading">로딩 중...</div>
        ) : (
          <>
            <div className="card-grid">
              {characters.map(character => (
                <div key={character.id} className="character-card">
                  <div className="card-image">
                    <img src={character.imageUrl || '/placeholder.png'} alt={character.cardName} />
                    <div className="card-badges">
                      <span className={`element-badge ${character.elementType.toLowerCase()}`}>
                        {elementIcons[character.elementType]}
                      </span>
                      <span className="weapon-badge">
                        {weaponIcons[character.weaponType]}
                      </span>
                    </div>
                  </div>
                  <div className="card-info">
                    <h3>{character.cardName}</h3>
                    <div className="card-stats">
                      <span>HP: {character.hp}</span>
                      <span>Energy: {character.maxEnergy}</span>
                    </div>
                  </div>
                </div>
              ))}
            </div>

            {characters.length === 0 && !loading && (
              <div className="no-results">
                <p>조건에 맞는 캐릭터 카드가 없습니다.</p>
              </div>
            )}
          </>
        )}
      </div>
    </div>
  )
}
