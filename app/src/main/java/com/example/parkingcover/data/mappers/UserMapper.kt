package com.example.parkingcover.data.mappers

import com.example.parkingcover.data.db.entities.UserEntity
import com.example.parkingcover.data.models.User

fun UserEntity.toModel() = User(
    userId, name
)

fun User.toEntity() = UserEntity(
    userId, name
)