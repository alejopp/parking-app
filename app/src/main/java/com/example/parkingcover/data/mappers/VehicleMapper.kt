package com.example.parkingcover.data.mappers

import com.example.parkingcover.data.db.entities.VehicleEntity
import com.example.parkingcover.data.models.Vehicle

fun VehicleEntity.toModel() = Vehicle(
    licensePlate, userOwner
)

fun Vehicle.toEntity() = VehicleEntity(
    licensePlate, userOwner
)