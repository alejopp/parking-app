package com.example.parkingcover

import android.app.Application
import androidx.room.Room
import com.example.parkingcover.data.db.ParkingDatabase
import com.example.parkingcover.utils.DATABASE_NAME
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ParkingCover: Application() {
    companion object{
        lateinit var database: ParkingDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, ParkingDatabase::class.java, DATABASE_NAME).build()
    }
}