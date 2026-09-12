package com.mitsubishidestinatorgls.ppb_mod2_kel07

sealed class Screen(val route: String, val title: String) {
    object Anime : Screen("anime", "Anime")
    object About : Screen("about", "About")
    object Detail : Screen("anime_detail/{animeId}", "Detail") {
        fun createRoute(animeId: Int) = "anime_detail/$animeId"
    }
}
