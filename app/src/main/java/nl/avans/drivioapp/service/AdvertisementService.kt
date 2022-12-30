package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.model.Reservation
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DrivioApiService {
    @GET("advertisement")
    suspend fun getAdvertisements(): List<Advertisement>

    @GET("advertisement/{advertisementId}")
    suspend fun getAdvertisementById(
        @Path(
            value = "advertisementId",
            encoded = false
        ) advertisementId: Int
    ): Response<Advertisement>

    @POST(value = "advertisement")
    suspend fun postAdvertisementWithResponse(@Body advertisement: Advertisement): Response<Unit>

    @POST("reservation")
    suspend fun postReservationWithResponse(@Body reservation: Reservation): Response<Unit>
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

object DrivioApi {
    val retrofitService: DrivioApiService by lazy {
        retrofit.create(DrivioApiService::class.java)
    }
}