package com.example.themangabook.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.themangabook.presentation.components.BottomNavigationBar
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.example.themangabook.presentation.navigation.Screen
import androidx.navigation.compose.composable
import com.example.themangabook.presentation.face.FaceRecognitionScreen
import com.example.themangabook.presentation.manga.MangaDetailsScreen
import com.example.themangabook.presentation.manga.MangaScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Manga.route,
            modifier = Modifier.padding(padding)
        ) {
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
}
