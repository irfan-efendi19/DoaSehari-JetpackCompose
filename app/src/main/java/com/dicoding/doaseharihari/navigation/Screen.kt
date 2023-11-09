package com.dicoding.doaseharihari.navigation


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailDoa : Screen("home/{doaId}") {
        fun createRoute(doaId: Long) = "home/$doaId"
    }
}