package com.example.themangabook.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.themangabook.presentation.components.BottomNavigationBar
import com.example.themangabook.presentation.navigation.NavGraph
import com.example.themangabook.presentation.navigation.Screen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            // Conditionally display the BottomNavigationBar based on the current route
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute in listOf(Screen.Manga.route, Screen.FaceRecognition.route)) {
            BottomNavigationBar(navController = navController)
        }
}
) {
    padding ->
    NavGraph(
        navController = navController,
        modifier = Modifier.padding(padding)
    )
}
}
