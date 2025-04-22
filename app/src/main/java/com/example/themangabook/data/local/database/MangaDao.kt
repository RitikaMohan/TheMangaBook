package com.example.themangabook.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themangabook.data.local.entity.MangaEntity

@Dao
interface MangaDao {

    @Query("SELECT * FROM manga")
    suspend fun getAllManga(): List<MangaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaList(mangaList: List<MangaEntity>)

    @Query("DELETE FROM manga")
    suspend fun clearManga()
}
