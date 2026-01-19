import axios from 'axios'

const API_BASE_URL = '/api'

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

// Character Cards API
export const characterCardAPI = {
  getAll: (params = {}) => apiClient.get('/cards/characters', { params }),
  getById: (id) => apiClient.get(`/cards/characters/${id}`),
  create: (data) => apiClient.post('/cards/characters', data),
  update: (id, data) => apiClient.put(`/cards/characters/${id}`, data),
  delete: (id) => apiClient.delete(`/cards/characters/${id}`),
}

// Action Cards API
export const actionCardAPI = {
  getAll: (params = {}) => apiClient.get('/cards/actions', { params }),
  getById: (id) => apiClient.get(`/cards/actions/${id}`),
  create: (data) => apiClient.post('/cards/actions', data),
  update: (id, data) => apiClient.put(`/cards/actions/${id}`, data),
  delete: (id) => apiClient.delete(`/cards/actions/${id}`),
}

// Decks API
export const deckAPI = {
  getAll: (params = {}) => apiClient.get('/decks', { params }),
  getById: (id) => apiClient.get(`/decks/${id}`),
  create: (data) => apiClient.post('/decks', data),
  update: (id, data) => apiClient.put(`/decks/${id}`, data),
  delete: (id) => apiClient.delete(`/decks/${id}`),
}

export default apiClient
