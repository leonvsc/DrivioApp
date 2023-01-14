package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.AWS.ImagesS3AWS
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.repository.ElectricCarRepository
import retrofit2.Response

private const val TAG = "AddElectricCarViewModel"

class AddElectricCarViewModel : ViewModel() {
//    private lateinit var imagesS3AWS: ImagesS3AWS
    private val electricCarRepository = ElectricCarRepository()

    private val _getElectricCarResponse: MutableLiveData<List<ElectricCar>> = MutableLiveData();
    val electricCarResponse: LiveData<List<ElectricCar>>
        get() = _getElectricCarResponse;

    private val _getElectricCarByIdResponse: MutableLiveData<Response<ElectricCar>> = MutableLiveData()
    val getElectricCarByIdResponse: LiveData<Response<ElectricCar>>
        get() = _getElectricCarByIdResponse

    private val _postElectricCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postElectricCarResponse: LiveData<Response<Unit>>
        get() = _postElectricCarResponse

    private val _deleteElectricCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val deleteElectricCarResponse: LiveData<Response<Unit>>
        get() = _deleteElectricCarResponse

    private val _putElectricCarResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val putElectricCarWithResponse: LiveData<Response<Unit>>
        get() = _putElectricCarResponse

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
            _postElectricCarResponse.value = electricCarRepository.postElectricCarWithResponse(electricCar);
        }
    }

    fun deleteElectricCarWithResponse(carId: Int) {
        viewModelScope.launch {
            _deleteElectricCarResponse.value = electricCarRepository.deleteElectricCarResponse(carId)
        }
    }

    fun putElectricCarWithResponse(electricCar: ElectricCar) {
        viewModelScope.launch {
            _putElectricCarResponse.value = electricCarRepository.putElectricCarWithResponse(electricCar);
        }
    }

    fun putImage(bucketName: String, objectKey: String, objectPath: String) {
        viewModelScope.launch {
            ImagesS3AWS().putS3Object(bucketName,objectKey,objectPath)
        }
    }
}