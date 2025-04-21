package com.example.themangabook.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themangabook.data.local.entity.MangaEntity
import com.example.themangabook.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, MangaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mangaDao(): MangaDao
}