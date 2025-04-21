package com.example.themangabook.presentation.manga

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MangaDetailsScreen(mangaId: String, viewModel: MangaViewModel = hiltViewModel()) {
    val manga = viewModel.uiState.mangaList.find { it.id == mangaId }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (manga != null) {
            Text(manga.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text(manga.description)
        } else {
            Text("Manga not found", color = Color.Red)
        }
    }
}
