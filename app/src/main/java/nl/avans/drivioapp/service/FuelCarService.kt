package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.FuelCar
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

//    @DELETE("fuelcar/{id}")
//    suspend fun deleteFuelCarResponse(@Path("id") carId: Int): Response<ResponseBody>
//
//    @DELETE("fuelcar/{id}")
//    suspend fun deleteFuelCar(@Path("id") carId: Int)

    @POST(value = "fuelcar")
    suspend fun postFuelCarWithResponse(@Body fuelCar: FuelCar): Response<Unit>

//    @POST(value = "fuelcar")
//    suspend fun postFuelCar(@Body todoItem: FuelCar): FuelCar

//    @PUT(value = "fuelcar/{id}")
//    suspend fun putFuelCar(@Body todoItem: FuelCar, @Path("id") todoId: Int): FuelCar
}
