package nl.avans.drivioapp.service

import nl.avans.drivioapp.model.Reservation
import retrofit2.Response
import retrofit2.http.*

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

    @PUT(value = "reservation/update")
    suspend fun putReservationWithResponse(@Body reservation: Reservation): Response<Unit>

    @DELETE("reservation/delete/{reservationId}")
    suspend fun deleteReservationWithResponse(@Path("ReservationId") reservationId: Int): Response<Unit>
}