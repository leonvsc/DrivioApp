package nl.avans.drivioapp.service

import nl.avans.drivioapp.model.Advertisement
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST(value = "advertisement")
    suspend fun postAdvertisementWithResponse(@Body advertisement: Advertisement): Response<Unit>

}