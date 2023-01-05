package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.repository.AdvertisementRepository
import retrofit2.Response

private const val TAG = "AdvertisementViewModel"

class AdvertisementViewModel : ViewModel() {
    private val advertisementRepository = AdvertisementRepository()
    private val _getAdvertisementResponse: MutableLiveData<List<Advertisement>> = MutableLiveData()
    val getAdvertisementResponse: LiveData<List<Advertisement>>
        get() = _getAdvertisementResponse

    private val _getAdvertisementByIdResponse: MutableLiveData<Response<Advertisement>> =
        MutableLiveData()
    val getAdvertisementByIdResponse: LiveData<Response<Advertisement>>
        get() = _getAdvertisementByIdResponse


    private val _postAdvertisementResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postAdvertisementResponse: LiveData<Response<Unit>>
        get() = _postAdvertisementResponse

    private val _deleteAdvertisementResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val deleteAdvertisementResponse: LiveData<Response<Unit>>
        get() = _deleteAdvertisementResponse
        
    private val _putAdvertisementResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val putAdvertisementResponse: LiveData<Response<Unit>>
        get() = _putAdvertisementResponse

    init {
        getAdvertisements()
    }

    fun getAdvertisements() {
        viewModelScope.launch {
            _getAdvertisementResponse.value = advertisementRepository.getAdvertisements()
        }
    }


    fun getAdvertisementById(advertisementId: Int) {
        viewModelScope.launch {
            _getAdvertisementByIdResponse.value =
                advertisementRepository.getAdvertisementById(advertisementId)
        }
    }

    fun postAdvertisementWithResponse(advertisement: Advertisement) {
        viewModelScope.launch {
            _postAdvertisementResponse.value =
                advertisementRepository.postAdvertisementWithResponse(advertisement)
        }
    }

    fun deleteAdvertisementWithResponse(advertisementId: Int) {
        viewModelScope.launch {
            _deleteAdvertisementResponse.value =
                advertisementRepository.deleteAdvertisementWIthResponse(advertisementId)
        }
        
    fun putAdvertisementWithResponse (advertisement: Advertisement) {
        viewModelScope.launch {
            _putAdvertisementResponse.value = advertisementRepository.putAdvertisementWithResponse(advertisement)
        }
    }
}