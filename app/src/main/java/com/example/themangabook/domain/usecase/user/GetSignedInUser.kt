package com.example.themangabook.domain.usecase.user

import com.example.themangabook.domain.model.User
import com.example.themangabook.domain.repository.UserRepository

class GetSignedInUser(private val repo: UserRepository) {
    suspend operator fun invoke(): User? {
        return repo.getSignedInUser()
    }
}
