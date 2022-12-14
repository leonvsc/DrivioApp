package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.service.DrivioApi
import org.json.JSONArray

private const val TAG = "AdvertisementViewModel"

class AdvertisementViewModel : ViewModel() {
    private val _advertisementResponse: MutableLiveData<List<Advertisement>> = MutableLiveData()
    val advertisementResponse: LiveData<List<Advertisement>>
        get() = _advertisementResponse

    init {
        getAdvertisements()
    }

    fun getAdvertisements() {
        viewModelScope.launch {
            _advertisementResponse.value = DrivioApi.retrofitService.getAdvertisements()
        }
    }


    // TODO: Need to be tested.
    fun getAdvertisementById(advertisementId: Int) {
        viewModelScope.launch {
            _advertisementResponse.value = DrivioApi.retrofitService.getAdvertisementById(advertisementId)
        }
    }
}