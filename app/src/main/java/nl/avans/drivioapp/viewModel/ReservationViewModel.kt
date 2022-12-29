package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.Reservation
import nl.avans.drivioapp.repository.ReservationRepository
import retrofit2.Response

class ReservationViewModel : ViewModel() {
    private val reservationRepository = ReservationRepository()


    private val _postReservationResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postReservationResponse: LiveData<Response<Unit>>
        get() = _postReservationResponse

    fun postReservationWithResponse(reservation: Reservation) {
        viewModelScope.launch {
            _postReservationResponse.value = reservationRepository.postReservationWithResponse(reservation)
        }
    }
}