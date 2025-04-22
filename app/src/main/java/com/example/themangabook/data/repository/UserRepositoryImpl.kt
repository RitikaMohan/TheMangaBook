package com.example.themangabook.data.repository

import com.example.themangabook.data.local.database.UserDao
import com.example.themangabook.data.local.entity.toDomain
import com.example.themangabook.domain.model.User
import com.example.themangabook.domain.model.toEntity
import com.example.themangabook.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun signIn(email: String, password: String): User {
        val existingUserEntity = userDao.getUserByEmail(email)
        return if (existingUserEntity != null) {
            if (existingUserEntity.password == password) {
                userDao.updateSignInStatus(email, true)
                existingUserEntity.copy(isSignedIn = true).toDomain()
            } else {
                throw Exception("Incorrect password")
            }
        } else {
            val newUser = User(email, password, true)
            userDao.insertUser(newUser.toEntity())
            newUser
        }
    }

    override suspend fun getSignedInUser(): User? {
        return userDao.getSignedInUser()
    }

    override suspend fun signOut(email: String) {
        userDao.updateSignInStatus(email, false)
    }

    override suspend fun registerUser(user: User) {
        val existing = userDao.getUserByEmail(user.email)
        if (existing != null) throw Exception("User already exists")
        userDao.insertUser(user.toEntity())
    }
}
