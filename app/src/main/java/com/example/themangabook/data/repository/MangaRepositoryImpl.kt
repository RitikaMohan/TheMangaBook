package com.example.themangabook.data.repository

import com.example.themangabook.data.local.database.MangaDao
import com.example.themangabook.data.local.entity.toDomain
import com.example.themangabook.data.remote.MangaApiService
import com.example.themangabook.data.remote.toEntity
import com.example.themangabook.domain.model.Manga
import com.example.themangabook.domain.repository.MangaRepository

class MangaRepositoryImpl(
    private val api: MangaApiService,
    private val mangaDao: MangaDao
) : MangaRepository {

    override suspend fun getManga(page: Int): List<Manga> {
        return try {
            val response = api.fetchManga(page).data
            val entities = response.map { it.toEntity() }
            mangaDao.insertMangaList(entities)
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            mangaDao.getAllManga().map { it.toDomain() } // Offline data fallback
        }
    }

    override suspend fun refreshManga(): List<Manga> {
        val response = api.fetchManga(1).data
        val entities = response.map { it.toEntity() }
        mangaDao.clearManga()
        mangaDao.insertMangaList(entities)
        return entities.map { it.toDomain() }
    }
}
