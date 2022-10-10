package com.example.parkingcover.ui.carlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingcover.data.repository.ParkingRepository
import com.example.parkingcover.data.models.ParkingSlotsLog
import com.example.parkingcover.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(private val parkingRepository: ParkingRepository) :
    ViewModel() {

    private val _status = MutableLiveData<ResponseStatus<Any>>()
    val status: LiveData<ResponseStatus<Any>> get() = _status

    private val _carsInParking = MutableLiveData<List<ParkingSlotsLog>?>()
    val carsInParking: LiveData<List<ParkingSlotsLog>?> get() = _carsInParking

    fun getCarsInParking() {
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            val response = parkingRepository.getLogs()
            if (response is ResponseStatus.Success){
                _carsInParking.value = response.data
                _status.value = ResponseStatus.Success(response.data)
            }
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }

}