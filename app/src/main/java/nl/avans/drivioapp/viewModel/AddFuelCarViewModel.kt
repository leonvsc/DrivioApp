package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.FuelCar
import nl.avans.drivioapp.repository.FuelCarRepository
import retrofit2.Response

private const val TAG = "AddFuelCarViewModel"

class AddFuelCarViewModel : ViewModel() {
    private val fuelCarRepository = FuelCarRepository()

    private val _getFuelCarResponse: MutableLiveData<List<FuelCar>> = MutableLiveData();
    val getFuelCarResponse: LiveData<List<FuelCar>>
        get() = _getFuelCarResponse;

    private val _getFuelCarByIdResponse: MutableLiveData<Response<FuelCar>> = MutableLiveData()
    val getFuelCarByIdResponse: LiveData<Response<FuelCar>>
        get() = _getFuelCarByIdResponse

    private val _postFuelCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postFuelCarResponse: LiveData<Response<Unit>>
        get() = _postFuelCarResponse
    init {
        getFuelCars();
    }

    fun getFuelCars() {
        viewModelScope.launch {
            _getFuelCarResponse.value = fuelCarRepository.getFuelCars();
        }
    }

    fun getFuelCarById(carId: Int) {
        viewModelScope.launch {
            _getFuelCarByIdResponse.value =
                fuelCarRepository.getFuelCarById(carId)
        }
    }

    fun postFuelCarWithResponse(fuelCar: FuelCar) {
        viewModelScope.launch {
            _postFuelCarResponse.value =
                fuelCarRepository.postFuelCarWithResponse(fuelCar)
        }
    }
}