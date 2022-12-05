package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.service.DrivioApi

private const val TAG = "AdvertismentViewModel"

class AdvertisementViewModel : ViewModel() {
    private val _advertismentResponse: MutableLiveData<String> = MutableLiveData()
    val advertisementResponse: LiveData<String>
        get() = _advertismentResponse

    init {
        getAdvertisements()
    }

    private fun getAdvertisements() {
        viewModelScope.launch {
            _advertismentResponse.value = DrivioApi.retrofitService.getAdvertisements()
        }
    }
}