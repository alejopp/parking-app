package com.example.parkingcover.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parking_slots_log")
data class ParkingSlotLogEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "license_plate") val licensePlate: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "time_in") val timeIn: Int,
    @ColumnInfo(name = "time_out") val timeOut: Int
)