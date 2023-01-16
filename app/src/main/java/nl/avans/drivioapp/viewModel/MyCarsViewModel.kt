package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.repository.ElectricCarRepository
import retrofit2.Response

private const val TAG = "MyCarsViewModel"

class MyCarsViewModel : ViewModel() {
    private val electricCarRepository = ElectricCarRepository()

    private val _getElectricCarResponse: MutableLiveData<List<ElectricCar>> = MutableLiveData();
    val electricCarResponse: LiveData<List<ElectricCar>>
        get() = _getElectricCarResponse;

    private val _getElectricCarByIdResponse: MutableLiveData<Response<ElectricCar>> =
        MutableLiveData()
    val getElectricCarByIdResponse: LiveData<Response<ElectricCar>>
        get() = _getElectricCarByIdResponse

    private val _postElectricCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postElectricCarResponse: LiveData<Response<Unit>>
        get() = _postElectricCarResponse

    private val _deleteFuelCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val deleteFuelCarResponse: LiveData<Response<Unit>>
        get() = _deleteFuelCarResponse

    private val _putFuelCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val putFuelCarResponse: LiveData<Response<Unit>>
        get() = _putFuelCarResponse

    init {
        getElectricCars();
    }

    fun getElectricCars() {
        viewModelScope.launch {
            _getElectricCarResponse.value = electricCarRepository.getElectricCars();
        }
    }

    fun getElectricCarById(carId: Int) {
        viewModelScope.launch {
            _getElectricCarByIdResponse.value = electricCarRepository.getElectricCarById(carId)
        }
    }

    fun postElectricCarWithResponse(electricCar: ElectricCar) {
        viewModelScope.launch {
            _postElectricCarResponse.value =
                electricCarRepository.postElectricCarWithResponse(electricCar);
        }
    }

    fun deleteElectricCarWithResponse(carId: Int) {
        viewModelScope.launch {
            _deleteFuelCarResponse.value =
                electricCarRepository.deleteElectricCarResponse(carId)
        }
    }

    fun putElectricCarWithResponse(electricCar: ElectricCar) {
        viewModelScope.launch {
            _putFuelCarResponse.value =
                electricCarRepository.putElectricCarWithResponse(electricCar)
        }
    }
}