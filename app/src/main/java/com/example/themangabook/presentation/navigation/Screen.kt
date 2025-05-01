package com.example.themangabook.presentation.navigation

sealed class Screen(val route: String) {
    object Launcher : Screen("launcher")
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    object Manga : Screen("Manga")
    object MangaDetails : Screen("manga_details/{mangaId}") {
        fun createRoute(mangaId: String) = "manga_details/$mangaId"
    }
    object FaceRecognition : Screen("FACE")
}
