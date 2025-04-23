package com.example.themangabook.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Manga : Screen("manga")
    object MangaDetails : Screen("manga/details/{mangaId}") {
        fun createRoute(mangaId: String) = "manga/details/$mangaId"
    }
    object FaceRecognition : Screen("face")
}
