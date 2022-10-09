package com.example.parkingcover.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.parkingcover.data.db.dao.ParkingSlotsDao
import com.example.parkingcover.data.db.dao.UserDao
import com.example.parkingcover.data.db.dao.VehicleDao
import com.example.parkingcover.data.db.entities.ParkingSlotLogEntity
import com.example.parkingcover.data.db.entities.UserEntity
import com.example.parkingcover.data.db.entities.VehicleEntity

@Database(entities = [UserEntity::class, VehicleEntity::class, ParkingSlotLogEntity::class], version = 1)
abstract class ParkingDatabase: RoomDatabase() {
    abstract fun getParkingSlotsDao(): ParkingSlotsDao
    abstract fun getUserDao(): UserDao
    abstract fun getVehiclesDao(): VehicleDao
}