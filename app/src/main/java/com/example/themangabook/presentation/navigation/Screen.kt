package com.example.themangabook.presentation.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen("sign_in")
    object Manga : Screen("manga")
    object MangaDetails : Screen("manga_details/{mangaId}") {
        fun createRoute(mangaId: String) = "manga_details/$mangaId"
    }
    object FaceRecognition : Screen("face_recognition")
}
