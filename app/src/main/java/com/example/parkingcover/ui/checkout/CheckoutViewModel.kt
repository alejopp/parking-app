package com.example.parkingcover.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingcover.data.models.ParkingSlotsLog
import com.example.parkingcover.data.repository.ParkingRepository
import com.example.parkingcover.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val parkingRepository: ParkingRepository) :
    ViewModel() {

    private val _status = MutableLiveData<ResponseStatus<Any>>()
    val status: LiveData<ResponseStatus<Any>> get() = _status

    private val _car = MutableLiveData<ResponseStatus<ParkingSlotsLog>>()
    val car: LiveData<ResponseStatus<ParkingSlotsLog>> get() = _car

    private var _totalTime = MutableLiveData<Int>()
    val totalTime: LiveData<Int> get() = _totalTime

    private var _totalPrice = MutableLiveData<Float>()
    val totalPrice: LiveData<Float> get() = _totalPrice


    private suspend fun setTimeOut(vehicleId: String, timeOut: Int){
        viewModelScope.launch {
            parkingRepository.setTimeOut(vehicleId, timeOut)
        }
    }

    fun calculateTotalTimeAndTotalPrice(vehicleId: String, timeOut: Int) {
        viewModelScope.launch {
            val response = parkingRepository.getLogWithoutCheckout(vehicleId)
            if (response is ResponseStatus.Success){
                getTotalTime(response.data.timeIn, timeOut)
                setTimeOut(vehicleId, timeOut)
            }
        }
    }

    private fun getTotalTime(timeIn: Int, timeOut: Int){
        _totalTime.value =  timeOut!! - timeIn
    }
}