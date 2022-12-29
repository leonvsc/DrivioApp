package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.ElectricCar
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ElectricCarService {
    @GET("electriccar")
//    suspend fun getAdvertisements(): List<Advertisement>
    suspend fun getElectricCars(): List<ElectricCar>;

    @GET("electriccar/{id}")
    suspend fun getElectricCarById(@Path("id") carId: Int): Response<ElectricCar>;

    @DELETE("electriccar/{id}")
    suspend fun deleteElectricCarResponse(@Path("id") carId: Int): Response<ResponseBody>

    @DELETE("electriccar/{id}")
    suspend fun deleteElectricCar(@Path("id") carId: Int)

    @POST(value = "electriccar")
    suspend fun postElectricCarWithResponse(@Body todoItem: ElectricCar): Response<ElectricCar>

    @POST(value = "electriccar")
    suspend fun postElectricCar(@Body todoItem: ElectricCar): ElectricCar

    @PUT(value = "electriccar/{id}")
    suspend fun putElectricCar(@Body todoItem: ElectricCar, @Path("id") todoId: Int): ElectricCar
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

object AddElectricCarAPI {
    val retrofitService: ElectricCarService by lazy {
        retrofit.create(ElectricCarService::class.java)
    }
}