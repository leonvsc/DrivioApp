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

    @GET("hydrogencar/{carId}")
    suspend fun getHydrogenCarById(
        @Path(
            value = "carId",
            encoded = false
        ) carId: Int
    ): Response<HydrogenCar>

    @POST(value = "hydrogencar")
    suspend fun postHydrogenCarWithResponse(@Body hydrogenCar: HydrogenCar): Response<Unit>

    @DELETE("hydrogencar/delete/{carId}")
    suspend fun deleteHydrogenCarWithResponse(@Path("id") carId: Int): Response<Unit>

    @PUT(value = "hydrogencar/update")
    suspend fun putHydrogenCarWithResponse(@Body hydrogenCar: HydrogenCar): Response<Unit>
}