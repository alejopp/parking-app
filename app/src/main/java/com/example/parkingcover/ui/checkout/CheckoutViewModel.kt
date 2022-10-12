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

    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice


    private suspend fun setTimeOut(vehicleId: String, timeOut: Int){
        viewModelScope.launch {
            parkingRepository.setTimeOut(vehicleId, timeOut)

        }
    }

    fun setTotalTimeAndTotalPrice(vehicleId: String, timeOut: Int) {
        viewModelScope.launch {
            val getCarWithoutCheckOut = parkingRepository.getLogWithoutCheckout(vehicleId)
            if (getCarWithoutCheckOut is ResponseStatus.Success){
                val totalTime = getTotalTime(getCarWithoutCheckOut.data.timeIn, timeOut)
                _totalTime.value = totalTime
                val totalPrice = getTotalPrice(totalTime)
                _totalPrice.value = totalPrice
                setTimeOut(vehicleId, timeOut)
            }
        }
    }

    private fun getTotalPrice(totalTime: Int): Double {
        val hours = totalTime / 100
        val minutes = totalTime % 100
        val totalMinutes = hours * 60 + minutes
        return totalMinutes / 60.0
    }

    private fun getTotalTime(timeIn: Int, timeOut: Int): Int{
        return  timeOut!! - timeIn
    }
}