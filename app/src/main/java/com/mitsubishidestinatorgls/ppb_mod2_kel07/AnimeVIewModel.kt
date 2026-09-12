package com.mitsubishidestinatorgls.ppb_mod2_kel07

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitsubishidestinatorgls.ppb_mod2_kel07.model.Anime
import com.mitsubishidestinatorgls.ppb_mod2_kel07.model.AnimeListResponse
import com.mitsubishidestinatorgls.ppb_mod2_kel07.model.AnimeResponse
import com.mitsubishidestinatorgls.ppb_mod2_kel07.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList

    private val _selectedAnime = MutableStateFlow<Anime?>(null)
    val selectedAnime: StateFlow<Anime?> = _selectedAnime

    fun fetchTopAnime() {
        viewModelScope.launch {
            try {
                val response: AnimeListResponse = ApiClient.service.getTopAnime()
                _animeList.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchAnime(query: String) {
        viewModelScope.launch {
            try {
                if (query.isBlank()) {
                    fetchTopAnime()
                } else {
                    val response: AnimeListResponse = ApiClient.service.searchAnime(query)
                    _animeList.value = response.data
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAnimeDetail(id: Int) {
        viewModelScope.launch {
            try {
                val response: AnimeResponse = ApiClient.service.getAnimeDetail(id)
                _selectedAnime.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
