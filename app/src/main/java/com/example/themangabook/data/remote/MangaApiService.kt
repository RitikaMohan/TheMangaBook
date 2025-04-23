package com.example.themangabook.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApiService {
    @GET("manga/fetch")
    suspend fun fetchManga(
        @Query("page") page: Int = 1,
        @Query("genres") genres: String = "Harem,Fantasy",
        @Query("nsfw") nsfw: Boolean = true,
        @Query("type") type: String = "all"
    ): MangaResponse
}