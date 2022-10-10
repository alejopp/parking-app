package com.example.parkingcover.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parkingcover.data.db.entities.UserEntity
import com.example.parkingcover.data.models.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT name FROM user WHERE user_id = :userId")
    suspend fun getUserName(userId: String): String
}