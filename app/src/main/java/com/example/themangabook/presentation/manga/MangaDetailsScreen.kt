package com.example.themangabook.presentation.manga

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MangaDetailsScreen(
    mangaId: String,
    viewModel: MangaViewModel = hiltViewModel()
) {
    val manga = viewModel.uiState.mangaList.find { it.id == mangaId }

    manga?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = manga.imageUrl,
                    contentDescription = manga.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = manga.title,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = manga.title, // Can be sub-title or other info
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                IconButton(onClick = { /* handle favorite */ }) {
                    Icon(Icons.Default.Star, contentDescription = "Favorite", tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = manga.description,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Manga not found", color = Color.Red)
        }
    }
}
