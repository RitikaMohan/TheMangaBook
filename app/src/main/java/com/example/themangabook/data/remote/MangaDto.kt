package com.example.themangabook.data.remote

import com.example.themangabook.data.local.entity.MangaEntity
import com.google.gson.annotations.SerializedName

data class MangaResponse(
    @SerializedName("data") val data: List<MangaDto>
)

data class MangaDto(
    val id: String,
    val title: String,
    val image_url: String,
    val description: String
)

fun MangaDto.toEntity(): MangaEntity {
    return MangaEntity(
        id = this.id,
        title = this.title,
        imageUrl = this.image_url,
        description = this.description
    )
}