package com.example.themangabook.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApiService {
    @GET("fetch-manga")
    suspend fun fetchManga(@Query("page") page: Int): MangaResponse
}