package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.service.DrivioApi

private const val TAG = "AdvertisementViewModel"

class AdvertisementViewModel : ViewModel() {
    private val _advertisementResponse: MutableLiveData<String> = MutableLiveData()
    val advertisementResponse: LiveData<String>
        get() = _advertisementResponse

    init {
        getAdvertisements()
    }

    fun getAdvertisements() {
        viewModelScope.launch {
            _advertisementResponse.value = DrivioApi.retrofitService.getAdvertisements()
        }
    }
}