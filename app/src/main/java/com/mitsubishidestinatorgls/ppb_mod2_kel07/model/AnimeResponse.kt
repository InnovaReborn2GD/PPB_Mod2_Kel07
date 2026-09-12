package com.mitsubishidestinatorgls.ppb_mod2_kel07.model

data class AnimeResponse(
    val data: Anime
)

data class Anime(
    val mal_id: Int,
    val title: String,
    val type: String?,
    val episodes: Int?,
    val score: Double?,
    val status: String?,
    val members: Int?,
    val aired: Aired?,
    val images: Images,
    val synopsis: String?
)

data class Aired(
    val string: String?
)

data class Images(
    val jpg: Jpg
)

data class Jpg(
    val image_url: String
)
