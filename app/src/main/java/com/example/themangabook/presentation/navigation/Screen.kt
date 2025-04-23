package com.example.themangabook.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Manga : Screen("manga")
    object MangaDetails : Screen("manga_details/{mangaId}") {
        fun createRoute(mangaId: String) = "manga_details/$mangaId"
    }
    object FaceRecognition : Screen("face")
}
