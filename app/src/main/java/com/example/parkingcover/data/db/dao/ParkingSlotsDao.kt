package com.example.parkingcover.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parkingcover.data.db.entities.ParkingSlotLogEntity

@Dao
interface ParkingSlotsDao {

    @Insert
    suspend fun insertLog(parkingSlotLog: ParkingSlotLogEntity)

    @Query("SELECT * FROM parking_slots_log")
    suspend fun getLogs(): List<ParkingSlotLogEntity>

    @Query("SELECT * FROM parking_slots_log WHERE license_plate = :vehicleId AND time_out IS NULL")
    suspend fun getLogWithoutCheckOut(vehicleId: String): ParkingSlotLogEntity

    @Query("UPDATE parking_slots_log SET time_out = :timeOut WHERE license_plate = :vehicleId AND time_out IS NULL")
    suspend fun setTimeOut(vehicleId: String, timeOut: Int)

}