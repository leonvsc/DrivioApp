package nl.avans.drivioapp.service

import nl.avans.drivioapp.model.Reservation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReservationService {
    @GET("reservation")
    suspend fun getReservations(): List<Reservation>

    @GET("reservation/{reservationId}")
    suspend fun getReservationById(
        @Path(
            value = "reservationId",
            encoded = false
        ) reservationId: Int
    ): Response<Reservation>

    @POST("reservation")
    suspend fun postReservationWithResponse(@Body reservation: Reservation): Response<Unit>
}