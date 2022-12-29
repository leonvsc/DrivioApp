package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.FuelCar
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface FuelCarService {
    @GET("fuelcar")
//    suspend fun getAdvertisements(): List<Advertisement>
    suspend fun getFuelCars(): List<FuelCar>;

//    @GET("electriccar/93")
////    suspend fun getAdvertisements(): List<Advertisement>
//    suspend fun getCarById(@Path("id") carId: Int): List<AddCar>;

    @DELETE("fuelcar/{id}")
    suspend fun deleteFuelCarResponse(@Path("id") carId: Int): Response<ResponseBody>

    @DELETE("fuelcar/{id}")
    suspend fun deleteFuelCar(@Path("id") carId: Int)

    @POST(value = "fuelcar")
    suspend fun postFuelCarWithResponse(@Body todoItem: FuelCar): Response<FuelCar>

    @POST(value = "fuelcar")
    suspend fun postFuelCar(@Body todoItem: FuelCar): FuelCar

    @PUT(value = "fuelcar/{id}")
    suspend fun putFuelCar(@Body todoItem: FuelCar, @Path("id") todoId: Int): FuelCar
}

private val BASE_URL = "https://drivio.nl/api/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object AddFuelCarAPI {
    val retrofitService: FuelCarService by lazy {
        retrofit.create(FuelCarService::class.java)
    }
}