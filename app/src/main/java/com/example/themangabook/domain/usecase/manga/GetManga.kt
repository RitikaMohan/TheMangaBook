package com.example.themangabook.domain.usecase.manga

import com.example.themangabook.domain.model.Manga
import com.example.themangabook.domain.repository.MangaRepository

class GetManga(private val repo: MangaRepository) {
    suspend operator fun invoke(page: Int): List<Manga> {
        return repo.getManga(page)
    }
}