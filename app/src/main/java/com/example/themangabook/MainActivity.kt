package com.example.themangabook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import com.example.themangabook.presentation.MainScreen
import com.example.themangabook.presentation.launcher.LauncherScreen
import com.example.themangabook.presentation.navigation.NavGraph
import com.example.themangabook.ui.theme.TheMangaBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            TheMangaBookTheme {
                MainScreen()
            }
        }
    }
}

