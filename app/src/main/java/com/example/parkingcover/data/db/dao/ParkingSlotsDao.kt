package com.example.parkingcover.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parkingcover.data.db.entities.ParkingSlotLogEntity
import com.example.parkingcover.data.models.ParkingSlotsLog

@Dao
interface ParkingSlotsDao {

    @Insert
    suspend fun insertLog(parkingSlotLog: ParkingSlotLogEntity)

    @Query("SELECT * FROM parking_slots_log")
    suspend fun getLogs(): List<ParkingSlotLogEntity>

    @Query("SELECT * FROM parking_slots_log WHERE (user_name = :userOrVehicleId OR license_plate = :userOrVehicleId) AND time_out = NULL")
    suspend fun getLogWithoutCheckOut(userOrVehicleId: String): ParkingSlotLogEntity

}