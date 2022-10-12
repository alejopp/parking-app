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

    companion object{
        const val NUMBER_OF_HUNDREDS = 100
        const val SECONDS_IN_A_MINUTE = 60
        const val PRICE_X_MINUTE = 60.0
    }
    private val _status = MutableLiveData<ResponseStatus<Any>>()
    val status: LiveData<ResponseStatus<Any>> get() = _status

    private val _car = MutableLiveData<ResponseStatus<ParkingSlotsLog>>()
    val car: LiveData<ResponseStatus<ParkingSlotsLog>> get() = _car

    private var _totalTime = MutableLiveData<Pair<Int,Int>>()
    val totalTime: LiveData<Pair<Int,Int>> get() = _totalTime

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

    private fun getTotalPrice(totalTime: Pair<Int,Int>): Double {
        val hours = totalTime.first
        val minutes = totalTime.second
        val totalMinutes = hours * SECONDS_IN_A_MINUTE + minutes
        return totalMinutes / PRICE_X_MINUTE
    }

    private fun getTotalTime(timeIn: Int, timeOut: Int): Pair<Int,Int>{
        val hoursTimeIn = timeIn / NUMBER_OF_HUNDREDS
        val minutesTimeIn = timeIn % NUMBER_OF_HUNDREDS
        val hoursTimeOut = timeOut / NUMBER_OF_HUNDREDS
        val minutesTimeOut = timeOut % NUMBER_OF_HUNDREDS
        var hoursTotalTime = hoursTimeOut - hoursTimeIn
        var minutesTotalTime = minutesTimeOut - minutesTimeIn
        if (minutesTotalTime < 0){
            hoursTotalTime--
            minutesTotalTime+= SECONDS_IN_A_MINUTE
        }

        return  Pair(hoursTotalTime, minutesTotalTime)
    }
}