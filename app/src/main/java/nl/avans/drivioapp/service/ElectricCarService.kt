package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.ElectricCar
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

//    @DELETE("electriccar/{id}")
//    suspend fun deleteElectricCarResponse(@Path("id") carId: Int): Response<ElectricCar>
//
//    @DELETE("electriccar/{id}")
//    suspend fun deleteElectricCar(@Path("id") carId: Int)
//
    @POST(value = "electriccar")
    suspend fun postElectricCarWithResponse(@Body electricCar: ElectricCar): Response<Unit>

//    @POST(value = "electriccar")
//    suspend fun postElectricCar(@Body electricCar: ElectricCar): ElectricCar

//    @PUT(value = "electriccar/{id}")
//    suspend fun putElectricCar(@Body electricCar: ElectricCar, @Path("id") todoId: Int): ElectricCar
}