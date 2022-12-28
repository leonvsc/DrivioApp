package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import nl.avans.drivioapp.model.Advertisement
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AdvertisementService {
    @GET("advertisement")
    suspend fun getAdvertisements(): List<Advertisement>

    @GET("advertisement/{advertisementId}")
    suspend fun getAdvertisementById(
        @Path(
            value = "advertisementId",
            encoded = false
        ) advertisementId: Int
    ): Response<Advertisement>
}
//
//private val BASE_URL = "https://drivio.nl/api/v1/"
//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//private val retrofit = Retrofit.Builder()
////    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()

//object DrivioApi {
//    val retrofitService: AdvertisementService by lazy {
//        retrofit.create(AdvertisementService::class.java)
//    }
//}