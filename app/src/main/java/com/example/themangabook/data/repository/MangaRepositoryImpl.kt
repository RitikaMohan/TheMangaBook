package com.example.themangabook.data.repository

import android.util.Log
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
            Log.d("Repo", "API Response size: ${response.size}")
            val entities = response.map { it.toEntity() }
            mangaDao.insertMangaList(entities)
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            Log.e("Repo", "API Failed: ${e.message}")
            val cached = mangaDao.getAllManga()
            Log.d("Repo", "Using cached: ${cached.size} items")
            cached.map { it.toDomain() }
        }
    }

    override suspend fun refreshManga(): List<Manga> {
        val response = api.fetchManga(1).data
        val entities = response.map { it.toEntity() }
        mangaDao.clearManga()
        mangaDao.insertMangaList(entities)
        Log.d("Repo", "Fetched manga count = ${entities.size}")
        return entities.map { it.toDomain() }
    }
}
