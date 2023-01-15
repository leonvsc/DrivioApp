package nl.avans.drivioapp.service

import nl.avans.drivioapp.model.ElectricCar
import retrofit2.Response
import retrofit2.http.*

interface ElectricCarService {
    @GET("electriccar")
    suspend fun getElectricCars(): List<ElectricCar>;

    @GET("electriccar/{carId}")
    suspend fun getElectricCarById(
        @Path(
            value = "carId",
            encoded = false
        ) carId: Int
    ): Response<ElectricCar>;

    @POST(value = "electriccar")
    suspend fun postElectricCarWithResponse(@Body electricCar: ElectricCar): Response<Unit>

    @DELETE("electriccar/delete/{carId}")
    suspend fun deleteElectricCarResponse(@Path("carId") carId: Int): Response<Unit>

    @PUT(value = "electriccar/update")
    suspend fun putElectricCarWithResponse(@Body electricCar: ElectricCar): Response<Unit>
}