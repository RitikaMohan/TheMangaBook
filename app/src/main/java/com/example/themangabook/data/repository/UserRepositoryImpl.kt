package com.example.themangabook.data.repository

import com.example.themangabook.data.local.database.UserDao
import com.example.themangabook.data.local.entity.toDomain
import com.example.themangabook.domain.model.User
import com.example.themangabook.domain.model.toEntity
import com.example.themangabook.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun isUserRegistered(email: String): Boolean {
        val user = userDao.getUserByEmail(email)
        return user != null
    }

    override suspend fun signIn(email: String, password: String): User {
        val existingUserEntity = userDao.getUserByEmail(email)
        if (existingUserEntity == null) {
            throw Exception("User not found")
        }
        if (existingUserEntity.password != password) {
            throw Exception("Incorrect password")
        }

        userDao.updateSignInStatus(email, true)
        return existingUserEntity.copy(isSignedIn = true).toDomain()
    }

    override suspend fun registerUser(user: User) {
        val existing = userDao.getUserByEmail(user.email)
        if (existing != null) throw Exception("User already exists")
        userDao.insertUser(user.toEntity())
    }

    override suspend fun getSignedInUser(): User? {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(email: String) {
        TODO("Not yet implemented")
    }
}