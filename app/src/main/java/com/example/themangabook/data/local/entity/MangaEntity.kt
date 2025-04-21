package com.example.themangabook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themangabook.domain.model.Manga

@Entity(tableName = "manga")
data class MangaEntity(
    @PrimaryKey val id: String,
    val title: String,
    val imageUrl: String,
    val description: String
)

fun MangaEntity.toDomain(): Manga {
    return Manga(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        description = this.description
    )
}