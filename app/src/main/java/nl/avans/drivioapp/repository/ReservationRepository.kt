package nl.avans.drivioapp.repository

import nl.avans.drivioapp.model.Reservation
import nl.avans.drivioapp.service.DrivioApi
import nl.avans.drivioapp.service.ReservationService
import retrofit2.Response

class ReservationRepository : ReservationService {
    override suspend fun getReservations(): List<Reservation> {
        return DrivioApi.reservationService.getReservations()
    }

    override suspend fun getReservationById(reservationId: Int): Response<Reservation> {
        return DrivioApi.reservationService.getReservationById(reservationId)
    }

    override suspend fun postReservationWithResponse(reservation: Reservation): Response<Unit> {
        return DrivioApi.reservationService.postReservationWithResponse(reservation)
    }
}