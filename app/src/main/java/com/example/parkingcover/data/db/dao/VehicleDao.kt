package com.example.parkingcover.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parkingcover.data.models.Vehicle

@Dao
interface VehicleDao {

    @Insert
    suspend fun insertVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM vehicle WHERE license_plate = :licensePlate")
    suspend fun getVehicle(licensePlate: Vehicle)
}