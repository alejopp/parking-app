package com.example.parkingcover.data.db.repository

import com.example.parkingcover.data.db.dao.ParkingSlotsDao
import com.example.parkingcover.data.db.dao.UserDao
import com.example.parkingcover.data.db.dao.VehicleDao
import com.example.parkingcover.data.db.entities.ParkingSlotLogEntity
import com.example.parkingcover.data.db.makeDatabaseCall
import com.example.parkingcover.data.mappers.toEntity
import com.example.parkingcover.data.mappers.toModel
import com.example.parkingcover.data.models.ParkingSlotsLog
import com.example.parkingcover.data.models.User
import com.example.parkingcover.data.models.Vehicle
import com.example.parkingcover.utils.ResponseStatus
import javax.inject.Inject

class ParkingRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val vehicleDao: VehicleDao,
    private val parkingSlotsDao: ParkingSlotsDao
) : ParkingRepository {
    //User
    override suspend fun insertUser(user: User) = makeDatabaseCall {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun getUserName(userId: String): ResponseStatus<String> = makeDatabaseCall {
        userDao.getUserName(userId)
    }

    //Vehicle
    override suspend fun insertVehicle(vehicle: Vehicle) = makeDatabaseCall {
        vehicleDao.insertVehicle(vehicle.toEntity())
    }

    override suspend fun getVehicle(licensePlate: String): ResponseStatus<Vehicle> =
        makeDatabaseCall {
            vehicleDao.getVehicle(licensePlate).toModel()
        }

    //Park slot log
    override suspend fun insertLog(parkingSlotsLog: ParkingSlotsLog): ResponseStatus<Unit> = makeDatabaseCall {
        parkingSlotsDao.insertLog(parkingSlotsLog.toEntity())
    }

    override suspend fun getLogs(): ResponseStatus<List<ParkingSlotsLog>> = makeDatabaseCall {
        parkingSlotsDao.getLogs().map { parkingSlotLogEntity ->
            parkingSlotLogEntity.toModel()
        }
    }

    override suspend fun getLogWithoutCheckout(userOrVehicleId: String): ResponseStatus<ParkingSlotsLog> =
        makeDatabaseCall {
            parkingSlotsDao.getLogWithoutCheckOut(userOrVehicleId).toModel()
        }
}