package com.example.themangabook.presentation.manga

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.themangabook.presentation.components.MangaCard

@Composable
fun MangaScreen(
    viewModel: MangaViewModel = hiltViewModel(),
    onMangaClick: (String) -> Unit
) {
    val state = viewModel.uiState

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
        } else if (state.errorMessage != null) {
            Text("Error: ${state.errorMessage}", color = Color.Red)
        } else {
            LazyColumn {
                items(state.mangaList) { manga ->
                    MangaCard(manga = manga, onClick = { onMangaClick(manga.id) })
                }
            }
        }
    }
}
