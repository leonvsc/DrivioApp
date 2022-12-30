package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.service.AddElectricCarAPI
import retrofit2.Response

private const val TAG = "MyCarsViewModel"

class MyCarsViewModel : ViewModel() {
    private val _electricCarResponse: MutableLiveData<List<ElectricCar>> = MutableLiveData();
    val electricCarResponse: LiveData<List<ElectricCar>>
        get() = _electricCarResponse;

    private val _getElectricCarByIdResponse: MutableLiveData<Response<ElectricCar>> = MutableLiveData()
    val getElectricCarByIdResponse: LiveData<Response<ElectricCar>>
        get() = _getElectricCarByIdResponse

    init {
        getElectricCars();
    }

    fun getElectricCars() {
        viewModelScope.launch {
            _electricCarResponse.value = AddElectricCarAPI.retrofitService.getElectricCars();
        }
    }

    fun getElectricCarById(carId: Int) {
        viewModelScope.launch {
            _getElectricCarByIdResponse.value = AddElectricCarAPI.retrofitService.getElectricCarById(carId)
        }
    }

    fun deleteElectricCar(carId: Int) {
        viewModelScope.launch {
            AddElectricCarAPI.retrofitService.deleteElectricCar(carId)
        }
    }
}