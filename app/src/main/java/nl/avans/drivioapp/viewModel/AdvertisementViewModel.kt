package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.service.DrivioApi

private const val TAG = "AdvertisementViewModel"

class AdvertisementViewModel : ViewModel() {
    private val _getAdvertisementResponse: MutableLiveData<List<Advertisement>> = MutableLiveData()
    val getAdvertisementResponse: LiveData<List<Advertisement>>
        get() = _getAdvertisementResponse

    private val _getAdvertisementByIdResponse: MutableLiveData<List<Advertisement>> = MutableLiveData()
        val getAdvertisementByIdResponse: LiveData<List<Advertisement>>
        get() = _getAdvertisementByIdResponse


    init {
        getAdvertisements()
    }

    fun getAdvertisements() {
        viewModelScope.launch {
            _getAdvertisementResponse.value = DrivioApi.retrofitService.getAdvertisements()
        }
    }


    // TODO: Need to be tested.
    fun getAdvertisementById(advertisementId: Int) {
        viewModelScope.launch {
            _getAdvertisementByIdResponse.value = DrivioApi.retrofitService.getAdvertisementById(advertisementId)
        }
    }
}