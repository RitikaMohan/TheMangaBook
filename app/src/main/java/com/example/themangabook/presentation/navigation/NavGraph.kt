package com.example.themangabook.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.themangabook.presentation.face.FaceRecognitionScreen
import com.example.themangabook.presentation.manga.MangaDetailsScreen
import com.example.themangabook.presentation.manga.MangaScreen
import com.example.themangabook.presentation.signin.SignInScreen

@Composable
fun NavGraph(startDestination: String = Screen.SignIn.route) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.SignIn.route) {
            SignInScreen(onSignedIn = {
                navController.navigate(Screen.Manga.route) {
                    popUpTo(Screen.SignIn.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Manga.route) {
            MangaScreen(onMangaClick = { mangaId ->
                navController.navigate(Screen.MangaDetails.createRoute(mangaId))
            })
        }

        composable(Screen.MangaDetails.route) { backStackEntry ->
            val mangaId = backStackEntry.arguments?.getString("mangaId") ?: return@composable
            MangaDetailsScreen(mangaId = mangaId)
        }

        composable(Screen.FaceRecognition.route) {
            FaceRecognitionScreen()
        }
    }
}