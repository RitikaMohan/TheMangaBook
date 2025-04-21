package com.example.themangabook.presentation.manga

import com.example.themangabook.domain.model.Manga

data class MangaUiState(
    val mangaList: List<Manga> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
