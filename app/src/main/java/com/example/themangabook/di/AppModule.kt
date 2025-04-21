package com.example.themangabook.di

import com.example.themangabook.data.local.database.MangaDao
import com.example.themangabook.data.local.database.UserDao
import com.example.themangabook.data.remote.MangaApiService
import com.example.themangabook.data.repository.MangaRepositoryImpl
import com.example.themangabook.data.repository.UserRepositoryImpl
import com.example.themangabook.domain.repository.MangaRepository
import com.example.themangabook.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideMangaRepository(
        api: MangaApiService,
        mangaDao: MangaDao
    ): MangaRepository {
        return MangaRepositoryImpl(api, mangaDao)
    }
}
