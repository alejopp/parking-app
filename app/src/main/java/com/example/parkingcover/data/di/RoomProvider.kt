package com.example.parkingcover.data.di

import android.content.Context
import androidx.room.Room
import com.example.parkingcover.data.db.ParkingDatabase
import com.example.parkingcover.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomProvider {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ParkingDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideUserDao(db: ParkingDatabase) = db.getUserDao()

    @Singleton
    @Provides
    fun provideVehicleDao(db: ParkingDatabase) = db.getVehiclesDao()

    @Singleton
    @Provides
    fun provideParkSlogLogDao(db: ParkingDatabase) = db.getParkingSlotsDao()


}