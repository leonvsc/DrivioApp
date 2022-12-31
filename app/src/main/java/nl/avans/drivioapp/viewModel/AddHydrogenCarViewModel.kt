package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.HydrogenCar
import nl.avans.drivioapp.service.AddElectricCarAPI
import nl.avans.drivioapp.service.AddHydrogenCarAPI

private const val TAG = "AddHydrogenCarViewModel"

class AddHydrogenCarViewModel : ViewModel() {
    private val _hydrogenCarResponse: MutableLiveData<List<HydrogenCar>> = MutableLiveData();
    val hydrogenCarResponse: LiveData<List<HydrogenCar>>
        get() = _hydrogenCarResponse;

    init {
        getHydrogenCars();
    }

    fun getHydrogenCars() {
        viewModelScope.launch {
            _hydrogenCarResponse.value = AddHydrogenCarAPI.retrofitService.getHydrogenCars();
        }
    }

    fun postHydrogenCar(hydrogenCar: HydrogenCar) {
        viewModelScope.launch {
            AddHydrogenCarAPI.retrofitService.postHydrogenCar(hydrogenCar);
            _hydrogenCarResponse.value = AddHydrogenCarAPI.retrofitService.getHydrogenCars();
        }
    }
}