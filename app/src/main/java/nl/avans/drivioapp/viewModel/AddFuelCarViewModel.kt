package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.FuelCar
import nl.avans.drivioapp.service.AddElectricCarAPI
import nl.avans.drivioapp.service.AddFuelCarAPI

private const val TAG = "AddFuelCarViewModel"

class AddFuelCarViewModel : ViewModel() {
    private val _fuelCarResponse: MutableLiveData<List<FuelCar>> = MutableLiveData();
    val electricCarResponse: LiveData<List<FuelCar>>
        get() = _fuelCarResponse;

    init {
        getFuelCars();
    }

    fun getFuelCars() {
        viewModelScope.launch {
            _fuelCarResponse.value = AddFuelCarAPI.retrofitService.getFuelCars();
        }
    }

    fun postFuelCar(fuelCar: FuelCar) {
        viewModelScope.launch {
            AddFuelCarAPI.retrofitService.postFuelCar(fuelCar);
            _fuelCarResponse.value = AddFuelCarAPI.retrofitService.getFuelCars();
        }
    }
}