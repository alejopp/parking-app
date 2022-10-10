package com.example.parkingcover.data.mappers

import com.example.parkingcover.data.db.entities.ParkingSlotLogEntity
import com.example.parkingcover.data.models.ParkingSlotsLog

fun ParkingSlotsLog.toEntity() = ParkingSlotLogEntity(
    id,
    licensePlate,
    userName,
    timeIn,
    timeOut
)

fun ParkingSlotLogEntity.toModel() = ParkingSlotsLog(
    id,
    licensePlate,
    userName,
    timeIn,
    timeOut
)