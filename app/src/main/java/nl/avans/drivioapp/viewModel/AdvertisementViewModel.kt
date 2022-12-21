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

    private val _postAdvertisementResponse: MutableLiveData<String> = MutableLiveData()
    val postAdvertisementResponse: LiveData<String>
        get() = _postAdvertisementResponse

    init {
        getAdvertisements()
    }

    fun getAdvertisements() {
        viewModelScope.launch {
            _advertisementResponse.value = DrivioApi.retrofitService.getAdvertisements()
        }
    }

    fun postAdvertisement(advertisement: Advertisement) {
        viewModelScope.launch {
            DrivioApi.retrofitService.postAdvertisement(advertisement)
//            _advertisementResponse.value = "postAdvertisement: ${advertisement} posted"
        }
    }

    fun postAdvertisementWithResponse(advertisement: Advertisement) {
        viewModelScope.launch {
            val postResponse = DrivioApi.retrofitService.postAdvertisementWithResponse(advertisement)
            _postAdvertisementResponse.value = "Response code: ${postResponse.code()}"
        }
    }
}