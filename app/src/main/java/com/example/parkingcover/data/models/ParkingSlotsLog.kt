package com.example.parkingcover.data.models

data class ParkingSlotsLog (
    val id: Int,
    val licensePlate: String,
    val userName: String,
    val timeIn: Int,
    val timeOut: Int
)