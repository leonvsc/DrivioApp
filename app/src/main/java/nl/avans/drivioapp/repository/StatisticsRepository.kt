package nl.avans.drivioapp.repository

import nl.avans.drivioapp.model.Statistic
import nl.avans.drivioapp.service.DrivioApi
import nl.avans.drivioapp.service.StatisticsService
import retrofit2.Response

class StatisticsRepository : StatisticsService {

    override suspend fun getStatistics(): List<Statistic> {
        return DrivioApi.statisticsService.getStatistics()
    }

    override suspend fun getStatisticById(statisticId: Int): Response<Statistic> {
        return DrivioApi.statisticsService.getStatisticById(statisticId)
    }

    override suspend fun postStatisticWithResponse(statistic: Statistic): Response<Unit> {
        return DrivioApi.statisticsService.postStatisticWithResponse(statistic)
    }

    override suspend fun deleteStatisticWithResponse(statisticId: Int): Response<Unit> {
        return DrivioApi.statisticsService.deleteStatisticWithResponse(statisticId)
    }

    override suspend fun putStatisticWithResponse(statistic: Statistic): Response<Unit> {
        return DrivioApi.statisticsService.putStatisticWithResponse(statistic)
    }
}