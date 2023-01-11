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

    private val _deleteHydrogenCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val deleteHydrogenCarResponse: LiveData<Response<Unit>>
        get() = _deleteHydrogenCarResponse

    private val _putHydrogenCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val putHydrogenCarResponse: LiveData<Response<Unit>>
        get() = _putHydrogenCarResponse

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

    fun deleteHydrogenCarWithResponse(carId: Int) {
        viewModelScope.launch {
            _deleteHydrogenCarResponse.value = hydrogenCarRepository.deleteHydrogenCarWithResponse(carId);
        }
    }

    fun putHydrogenCarWithResponse(hydrogenCar: HydrogenCar) {
        viewModelScope.launch {
            _putHydrogenCarResponse.value = hydrogenCarRepository.putHydrogenCarWithResponse(hydrogenCar);
        }
    }
}