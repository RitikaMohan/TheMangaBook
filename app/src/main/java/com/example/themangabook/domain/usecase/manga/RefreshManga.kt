package com.example.themangabook.domain.usecase.manga

import com.example.themangabook.domain.model.Manga
import com.example.themangabook.domain.repository.MangaRepository

class RefreshManga(private val repo: MangaRepository) {
    suspend operator fun invoke(): List<Manga> {
        return repo.refreshManga()
    }
}