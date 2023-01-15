package nl.avans.drivioapp.service

import nl.avans.drivioapp.model.FuelCar
import retrofit2.Response
import retrofit2.http.*

interface FuelCarService {
    @GET("fuelcar")
    suspend fun getFuelCars(): List<FuelCar>;

    @GET("fuelcar/{carId}")
    suspend fun getFuelCarById(
        @Path(
            value = "carId",
            encoded = false
        ) carId: Int
    ): Response<FuelCar>

    @POST(value = "fuelcar")
    suspend fun postFuelCarWithResponse(@Body fuelCar: FuelCar): Response<Unit>

    @DELETE("fuelcar/delete/{id}")
    suspend fun deleteFuelCarResponse(@Path("id") carId: Int): Response<Unit>

    @PUT(value = "fuelcar/update")
    suspend fun putFuelCarWithResponse(@Body fuelCar: FuelCar): Response<Unit>
}
