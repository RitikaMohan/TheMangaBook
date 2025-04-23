package com.example.themangabook.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.themangabook.presentation.navigation.Screen
import java.util.Locale

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.Manga, Screen.FaceRecognition)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(Screen.Manga.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (screen == Screen.Manga) Icons.Default.Home else Icons.Default.Face,
                        contentDescription = screen.route
                    )
                },
                label = { Text(screen.route.replaceFirstChar { it.uppercase() }) }
            )
        }
    }
}
