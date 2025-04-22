package com.example.themangabook.domain.repository

import com.example.themangabook.domain.model.User


interface UserRepository {
    suspend fun signIn(email: String, password: String): User
    suspend fun registerUser(user: User)
    suspend fun getSignedInUser(): User?
    suspend fun signOut(email: String)
}
