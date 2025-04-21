package com.example.themangabook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themangabook.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val email: String,
    val password: String,
    val isSignedIn: Boolean = false
)

fun UserEntity.toDomain(): User {
    return User(
        email = this.email,
        password = this.password,
        isSignedIn = this.isSignedIn
    )
}