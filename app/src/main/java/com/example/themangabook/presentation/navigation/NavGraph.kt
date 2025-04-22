package com.example.themangabook.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.themangabook.presentation.face.FaceRecognitionScreen
import com.example.themangabook.presentation.manga.MangaDetailsScreen
import com.example.themangabook.presentation.manga.MangaScreen
import com.example.themangabook.presentation.signin.LoginScreen
import com.example.themangabook.presentation.signin.RegisterScreen
import android.util.Log

@Composable
fun NavGraph(startDestination: String = Screen.Manga.route) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

//        composable(Screen.Login.route) {
//            LoginScreen(
//                onLoginSuccess = {
//                    navController.navigate(Screen.Manga.route) {
//                        popUpTo(Screen.Login.route) { inclusive = true }
//                    }
//                },
//                onRegisterClick = {
//                    navController.navigate(Screen.Register.route)
//                }
//            )
//        }
//
//        composable(Screen.Register.route) {
//            RegisterScreen(
//                onRegisterSuccess = {
//                    navController.navigate(Screen.Manga.route) {
//                        popUpTo(Screen.Register.route) { inclusive = true }
//                    }
//                },
//                onBackToLogin = {
//                    navController.popBackStack()
//                }
//            )
//        }

        composable(Screen.Manga.route) {
            Log.d("NavGraph", "Navigated to Manga screen")
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