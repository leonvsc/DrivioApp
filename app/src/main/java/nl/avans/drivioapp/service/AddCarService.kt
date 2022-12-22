package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.AddCar
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface AddCarService {
    @GET("electriccar")
//    suspend fun getAdvertisements(): List<Advertisement>
    suspend fun getCars(): List<AddCar>;

//    @GET("electriccar/93")
////    suspend fun getAdvertisements(): List<Advertisement>
//    suspend fun getCarById(@Path("id") carId: Int): List<AddCar>;

    @DELETE("electriccar/{id}")
    suspend fun deleteElectricCarResponse(@Path("id") carId: Int): Response<ResponseBody>

    @DELETE("electriccar/{id}")
    suspend fun deleteElectricCar(@Path("id") carId: Int)

    @POST(value = "electriccar")
    suspend fun postElectricCarWithResponse(@Body todoItem: AddCar): Response<AddCar>

    @POST(value = "electriccar")
    suspend fun postElectricCar(@Body todoItem: AddCar): AddCar

    @PUT(value = "electriccar/{id}")
    suspend fun putElectricCar(@Body todoItem: AddCar, @Path("id") todoId: Int): AddCar
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

object AddCarAPI {
    val retrofitService: AddCarService by lazy {
        retrofit.create(AddCarService::class.java)
    }
}