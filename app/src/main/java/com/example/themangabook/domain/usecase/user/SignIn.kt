package com.example.themangabook.domain.usecase.user

import com.example.themangabook.domain.model.User
import com.example.themangabook.domain.repository.UserRepository

class SignIn(private val repo: UserRepository) {
    suspend operator fun invoke(email: String, password: String): User {
        return repo.signIn(email, password)
    }
}
