package com.example.themangabook.domain.model

import com.example.themangabook.data.local.entity.UserEntity

data class User(
    val email: String,
    val password: String,
    val isSignedIn: Boolean
)

fun User.toEntity(): UserEntity {
    return UserEntity(
        email = this.email,
        password = this.password,
        isSignedIn = this.isSignedIn
    )
}