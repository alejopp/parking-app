package com.example.parkingcover.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle")
data class VehicleEntity (
    @PrimaryKey @ColumnInfo(name = "license_plate") val licensePlate: String
)