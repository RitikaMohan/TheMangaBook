package com.example.themangabook.domain.repository

import com.example.themangabook.domain.model.Manga

interface MangaRepository {
    suspend fun getManga(page: Int): List<Manga>
    suspend fun refreshManga(): List<Manga>
}