package nl.avans.drivioapp.service

import nl.avans.drivioapp.model.Advertisement
import retrofit2.Response
import retrofit2.http.*

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


    @DELETE("advertisement/delete/{advertisementId}")
    suspend fun deleteAdvertisementWithResponse(@Path("advertisementId") advertisementId: Int): Response<Unit>

    @PUT(value = "advertisement/update")
    suspend fun putAdvertisementWithResponse(@Body advertisement: Advertisement): Response<Unit>

}
