package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.HydrogenCar
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface HydrogenCarService {
    @GET("hydrogencar")
    suspend fun getHydrogenCars(): List<HydrogenCar>;

    @DELETE("hydrogencar/{id}")
    suspend fun deleteHydrogenCarResponse(@Path("id") carId: Int): Response<ResponseBody>

    @DELETE("hydrogencar/{id}")
    suspend fun deleteHydrogenCar(@Path("id") carId: Int)

    @POST(value = "hydrogencar")
    suspend fun postHydrogenCarWithResponse(@Body todoItem: HydrogenCar): Response<HydrogenCar>

    @POST(value = "hydrogencar")
    suspend fun postHydrogenCar(@Body todoItem: HydrogenCar): HydrogenCar

    @PUT(value = "hydrogencar/{id}")
    suspend fun putHydrogenCar(@Body todoItem: HydrogenCar, @Path("id") todoId: Int): HydrogenCar
}

private val BASE_URL = "https://drivio.nl/api/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object AddHydrogenCarAPI {
    val retrofitService: HydrogenCarService by lazy {
        retrofit.create(HydrogenCarService::class.java)
    }
}