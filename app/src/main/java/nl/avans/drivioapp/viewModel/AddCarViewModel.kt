package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.AddCar
import nl.avans.drivioapp.service.AddCarAPI

private const val TAG = "AddCarViewModel"

class AddCarViewModel : ViewModel() {
    private val _addCarResponse: MutableLiveData<List<AddCar>> = MutableLiveData();
    val addCarResponse: LiveData<List<AddCar>>
        get() = _addCarResponse;

    init {
        getCars();
    }

    fun getCars() {
        viewModelScope.launch {
            _addCarResponse.value = AddCarAPI.retrofitService.getCars();
        }
    }

    fun postElectricCars(addCar: AddCar) {
        viewModelScope.launch {
            AddCarAPI.retrofitService.postElectricCar(addCar);
            _addCarResponse.value = AddCarAPI.retrofitService.getCars();
        }
    }
}