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

    private val _getReservationsResponse: MutableLiveData<List<Reservation>> = MutableLiveData()
    val getReservationsResponse: LiveData<List<Reservation>>
        get() = _getReservationsResponse

    private val _getReservationByIdResponse: MutableLiveData<Response<Reservation>> =
        MutableLiveData()
    val getReservationByIdResponse: LiveData<Response<Reservation>>
        get() = _getReservationByIdResponse

    private val _postReservationResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postReservationResponse: LiveData<Response<Unit>>
        get() = _postReservationResponse

    private val _putReservationResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val putReservationResponse: LiveData<Response<Unit>>
        get() = _putReservationResponse

    private val _deleteReservationResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val deleteReservationResponse: LiveData<Response<Unit>>
        get() = _deleteReservationResponse

    init {
        getReservations()
    }

    fun getReservations() {
        viewModelScope.launch {
            _getReservationsResponse.value = reservationRepository.getReservations()
        }
    }

    fun getReservationById(reservationId: Int) {
        viewModelScope.launch {
            _getReservationByIdResponse.value =
                reservationRepository.getReservationById(reservationId)
        }
    }

    fun postReservationWithResponse(reservation: Reservation) {
        viewModelScope.launch {
            _postReservationResponse.value =
                reservationRepository.postReservationWithResponse(reservation)
        }
    }

    fun putReservationWithResponse(Reservation: Reservation) {
        viewModelScope.launch {
            _putReservationResponse.value =
                reservationRepository.putReservationWithResponse(Reservation)
        }
    }

    fun deleteReservationWithResponse(ReservationId: Int) {
        viewModelScope.launch {
            _deleteReservationResponse.value =
                reservationRepository.deleteReservationWithResponse(ReservationId)
        }
    }
}