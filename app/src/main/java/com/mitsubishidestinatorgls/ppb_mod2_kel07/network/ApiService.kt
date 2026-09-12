package com.mitsubishidestinatorgls.ppb_mod2_kel07.network

import com.mitsubishidestinatorgls.ppb_mod2_kel07.model.AnimeListResponse
import com.mitsubishidestinatorgls.ppb_mod2_kel07.model.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse

    @GET("anime")
    suspend fun searchAnime(@Query("q") query: String): AnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: Int): AnimeResponse
}
