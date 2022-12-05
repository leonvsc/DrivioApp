package nl.avans.drivioapp.service

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface DrivioApiService {
    @GET("advertisement")
    suspend fun getAdvertisements(): String
}

private val BASE_URL = "https://drivio.nl/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object DrivioApi {
    val retrofitService: DrivioApiService by lazy {
        retrofit.create(DrivioApiService::class.java)
    }
}