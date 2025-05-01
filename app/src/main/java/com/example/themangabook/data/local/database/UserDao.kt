package com.example.themangabook.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themangabook.data.local.entity.UserEntity
import com.example.themangabook.domain.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("UPDATE users SET isSignedIn = :status WHERE email = :email")
    suspend fun updateSignInStatus(email: String, status: Boolean)

    @Query("SELECT * FROM users WHERE isSignedIn = 1 LIMIT 1")
    suspend fun getSignedInUser(): UserEntity?
}