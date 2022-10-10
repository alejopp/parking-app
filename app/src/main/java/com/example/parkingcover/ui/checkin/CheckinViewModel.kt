package com.example.parkingcover.ui.checkin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingcover.R
import com.example.parkingcover.data.repository.ParkingRepository
import com.example.parkingcover.data.models.ParkingSlotsLog
import com.example.parkingcover.data.models.User
import com.example.parkingcover.data.models.Vehicle
import com.example.parkingcover.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckinViewModel @Inject constructor(private val parkingRepository: ParkingRepository) :
    ViewModel() {

    private val _status = MutableLiveData<ResponseStatus<Any>>()
    val status: LiveData<ResponseStatus<Any>> get() = _status

    private fun insertUserIntoDatabase(userId: String, userName: String) {
        viewModelScope.launch {
            val user = User(userId, userName)
            parkingRepository.insertUser(user)
        }
    }

    private fun insertVehicleIntoDatabase(licensePlate: String, userId: String) {
        viewModelScope.launch {
            val vehicle = Vehicle(licensePlate, userId)
            parkingRepository.insertVehicle(vehicle)
        }
    }

    private fun insertParkingSlotLogIntoDatabase(
        userId: String,
        userName: String,
        licensePlate: String,
        timeIn: Int,
        timeOut: Int?
    ) {
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            insertUserIntoDatabase(userId, userName)
            insertVehicleIntoDatabase(licensePlate, userId)
            val parkingSlotLog = ParkingSlotsLog(
                licensePlate = licensePlate, userName = userName,
                timeIn = timeIn, timeOut = timeOut
            )
            val response = parkingRepository.insertLog(parkingSlotLog)
            if (response is ResponseStatus.Success) {
                _status.value = ResponseStatus.Success(response.data)
            }
            if (response is ResponseStatus.Error) {
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }

    fun isCarRegistered(
        userId: String,
        userName: String,
        licensePlate: String,
        timeIn: Int,
        timeOut: Int?
    ) {
        _status.value = ResponseStatus.Loading()
        viewModelScope.launch {
            val response = parkingRepository.getLogWithoutCheckout(licensePlate)
            if (response is ResponseStatus.Success) {
                _status.value = ResponseStatus.Error(R.string.car_is_already_in)
            }
            if (response is ResponseStatus.Error) {
                insertParkingSlotLogIntoDatabase(userId, userName, licensePlate, timeIn, timeOut)
            }
        }
    }


}