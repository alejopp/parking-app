package com.example.parkingcover.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = "user_id") val userId:String,
    @ColumnInfo(name = "name") val name: String
)
