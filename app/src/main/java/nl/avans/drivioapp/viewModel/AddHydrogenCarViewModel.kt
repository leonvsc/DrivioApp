package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.HydrogenCar
import nl.avans.drivioapp.repository.HydrogenCarRepository
import retrofit2.Response

private const val TAG = "AddHydrogenCarViewModel"

class AddHydrogenCarViewModel : ViewModel() {
    private val hydrogenCarRepository = HydrogenCarRepository()

    private val _getHydrogenCarResponse: MutableLiveData<List<HydrogenCar>> = MutableLiveData();
    val getHydrogenCarResponse: LiveData<List<HydrogenCar>>
        get() = _getHydrogenCarResponse;

    private val _getHydrogenCarByIdResponse: MutableLiveData<Response<HydrogenCar>> = MutableLiveData()
    val getHydrogenCarByIdResponse: LiveData<Response<HydrogenCar>>
        get() = _getHydrogenCarByIdResponse

    private val _postHydrogenCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postHydrogenCarResponse: LiveData<Response<Unit>>
        get() = _postHydrogenCarResponse

    init {
        getHydrogenCars();
    }

    fun getHydrogenCars() {
        viewModelScope.launch {
            _getHydrogenCarResponse.value = hydrogenCarRepository.getHydrogenCars();
        }
    }

    fun getHydrogenCarById(carId: Int) {
        viewModelScope.launch {
            _getHydrogenCarByIdResponse.value = hydrogenCarRepository.getHydrogenCarById(carId)
        }
    }

    fun postHydrogenCarWithResponse(hydrogenCar: HydrogenCar) {
        viewModelScope.launch {
            _postHydrogenCarResponse.value = hydrogenCarRepository.postHydrogenCarWithResponse(hydrogenCar);
        }
    }
}