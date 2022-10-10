package com.example.parkingcover.data.db.repository

import com.example.parkingcover.data.db.entities.ParkingSlotLogEntity
import com.example.parkingcover.data.models.ParkingSlotsLog
import com.example.parkingcover.data.models.User
import com.example.parkingcover.data.models.Vehicle
import com.example.parkingcover.utils.ResponseStatus

interface ParkingRepository {

    //User
    suspend fun insertUser(user: User): ResponseStatus<Unit>
    suspend fun getUserName(userId: String): ResponseStatus<String>

    //Vehicle
    suspend fun insertVehicle(vehicle: Vehicle): ResponseStatus<Unit>
    suspend fun getVehicle(licensePlate: String): ResponseStatus<Vehicle>

    //Parking slots log
    suspend fun insertLog(parkingSlotsLog: ParkingSlotsLog): ResponseStatus<Unit>
    suspend fun getLogs(): ResponseStatus<List<ParkingSlotsLog>>
    suspend fun getLogWithoutCheckout(userOrVehicleId: String): ResponseStatus<ParkingSlotsLog>
}