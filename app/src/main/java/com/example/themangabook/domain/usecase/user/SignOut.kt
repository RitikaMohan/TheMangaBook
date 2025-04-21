package com.example.themangabook.domain.usecase.user

import com.example.themangabook.domain.repository.UserRepository

class SignOut(private val repo: UserRepository) {
    suspend operator fun invoke(email: String) {
        repo.signOut(email)
    }
}