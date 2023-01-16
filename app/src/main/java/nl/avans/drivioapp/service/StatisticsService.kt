package nl.avans.drivioapp.service

import nl.avans.drivioapp.model.Statistic
import retrofit2.Response
import retrofit2.http.*

interface StatisticsService {
    @GET("statistic")
    suspend fun getStatistics(): List<Statistic>

    @GET("Statistic/{statisticId}")
    suspend fun getStatisticById(
        @Path(
            value = "statisticId",
            encoded = false
        ) statisticId: Int
    ): Response<Statistic>

    @POST(value = "statistic")
    suspend fun postStatisticWithResponse(@Body statistic: Statistic): Response<Unit>

    @DELETE("statistic/delete/{statisticId}")
    suspend fun deleteStatisticWithResponse(@Path("statisticId") statisticId: Int): Response<Unit>

    @PUT(value = "statistic/update")
    suspend fun putStatisticWithResponse(@Body statistic: Statistic): Response<Unit>
}