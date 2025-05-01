package com.example.themangabook.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.themangabook.presentation.signin.LoginScreen
import com.example.themangabook.presentation.signin.RegisterScreen
import com.example.themangabook.presentation.face.FaceRecognitionScreen
import com.example.themangabook.presentation.manga.MangaDetailsScreen
import com.example.themangabook.presentation.manga.MangaScreen
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.themangabook.presentation.launcher.LauncherScreen
import com.example.themangabook.presentation.signin.LoginViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(navController = navController, startDestination = Screen.Launcher.route, modifier = modifier) {

        composable(Screen.Launcher.route) {
            LauncherScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Manga.route) {
                        popUpTo(Screen.Launcher.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Launcher.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Manga.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Screen.Register.route)
                },
                onForgotPasswordClick = {
                    // Optional
                }
            )
        }


        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Manga.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
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