package nl.avans.drivioapp.repository

import nl.avans.drivioapp.model.HydrogenCar
import nl.avans.drivioapp.service.DrivioApi
import nl.avans.drivioapp.service.HydrogenCarService
import retrofit2.Response

class HydrogenCarRepository : HydrogenCarService {
    override suspend fun getHydrogenCars(): List<HydrogenCar> {
        return DrivioApi.hydrogenCarService.getHydrogenCars()
    }

    override suspend fun getHydrogenCarById(carId: Int): Response<HydrogenCar> {
        return DrivioApi.hydrogenCarService.getHydrogenCarById(carId)
    }

    override suspend fun postHydrogenCarWithResponse(hydrogenCar: HydrogenCar): Response<Unit> {
        return DrivioApi.hydrogenCarService.postHydrogenCarWithResponse(hydrogenCar)
    }
}